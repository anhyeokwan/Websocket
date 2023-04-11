package kr.or.dm.model.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.or.dm.model.vo.DirectMessage;

public class DirectMessageHandler extends TextWebSocketHandler{
	
	// 쪽지 소켓에 접속한 회원정보 저장할  Map(key : id, value : 접속시간)
	HashMap<String, WebSocketSession> connectionMembers;
	
	
	
	public DirectMessageHandler() {
		super();
		// TODO Auto-generated constructor stub
		connectionMembers = new HashMap<String, WebSocketSession>();
	}

	@Autowired
	private DirectMessageService service;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{ //onopen에서 받는 곳은 여기다!
		JsonParser parser = new JsonParser();
		System.out.println("전송 메세지 : " + message.getPayload());
		
		JsonElement element = parser.parse(message.getPayload());
		// 전송데이터 목적 구분위한 type 값 확인
		String type = element.getAsJsonObject().get("type").getAsString(); // js의 키값으로 꺼내줌
		if(type.equals("enter")) {
			String memberId = element.getAsJsonObject().get("memberId").getAsString(); // js의 키값으로 꺼내줌
			connectionMembers.put(memberId, session);
			// 최초 접속시 현재 내가 읽지 앟ㄴ은 쪽지 수를 리턴
			int dmCount = service.dmCount(memberId);
			JsonObject obj = new JsonObject();
	        obj.addProperty("type", "myDmCount");
	        obj.addProperty("dmCount", dmCount);
	        String resultStr = new Gson().toJson(obj);

	        TextMessage tm = new TextMessage(resultStr);
			session.sendMessage(tm);
		}else if(type.equals("sendDm")) {
	         // 쪽지보내기 한 경우
	         // 1) DB에 dm insert
	         String receiver = element.getAsJsonObject().get("receiver").getAsString();
	         String sender = element.getAsJsonObject().get("sender").getAsString();
	         String dmContent = element.getAsJsonObject().get("dmContent").getAsString();
	         DirectMessage dm = new DirectMessage();
	         dm.setSender(sender);
	         dm.setReceiver(receiver);
	         dm.setDmContent(dmContent);
	         int result =  service.insertDm(dm);
	         // 1. 쪽지 보낸사람에게 성공실패여부
	         JsonObject obj = new JsonObject();
	         obj.addProperty("type", "sendDmResult");
	         obj.addProperty("sendResult", result);
	         String resultStr = new Gson().toJson(obj);
	         TextMessage senderMsg = new TextMessage(String.valueOf(resultStr));
	         session.sendMessage(senderMsg);
	         // 2. 쪽지 받은사람에게 받은 쪽지 갯수 전송
	         
		     WebSocketSession receiverSerssion =  connectionMembers.get(receiver);
		     if(receiverSerssion != null) {
		    	 JsonObject obj2 = new JsonObject();
			     obj2.addProperty("type", "myDmCount");
			     obj2.addProperty("dmCount", result);
			     String resultStr2 = new Gson().toJson(obj2);
			     TextMessage tm2 = new TextMessage(resultStr2);
			     receiverSerssion.sendMessage(tm2);
		     }
		}else if(type.equals("readCheck")){
        	String sender = element.getAsJsonObject().get("sender").getAsString();
        	
        	String receiver = element.getAsJsonObject().get("receiver").getAsString();
        	
        	// 쪽지 읽은 회원 읽지 않은 쪽지 수 갱신
        	int dmCount = service.dmCount(receiver);
        	JsonObject obj1 = new JsonObject();
	        obj1.addProperty("type", "myDmCount");
	        obj1.addProperty("dmCount", dmCount);
	        String resultStr = new Gson().toJson(obj1);

	        TextMessage tm1 = new TextMessage(resultStr);
			session.sendMessage(tm1);
        	
        	WebSocketSession receiverSession = connectionMembers.get(sender);
        	if(receiverSession != null) {
        		JsonObject obj = new JsonObject();
        		obj.addProperty("type", "readDm");
        		String resultMsg = new Gson().toJson(obj);
        		TextMessage tm = new TextMessage(resultMsg);
        		receiverSession.sendMessage(tm);
        	}
     }

	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		// 접속해제한 멤버를 맴에서 제거
		connectionMembers.values().remove(session);
	}
}

















