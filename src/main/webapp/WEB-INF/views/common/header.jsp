<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<h3>읽지 않은 쪽지 수 : <span id="dmCount"></span></h3>
<input type="hidden" id="memberId" value="${sessionScope.m.memberId }">

<script>
	let memberid;
	let ws;
	$(function(){
		memberid = $("#memberId").val();
		ws = new WebSocket("ws://192.168.219.102/dm.do");
		// 함수 연결해주는 것
		ws.onopen = onopen;
		ws.onmessage = receiveMsg;
		ws.onclose = onClose;
	});
	
	function onopen(){
		console.log("쪽지소켓연결완료");
		console.log(memberid);
		const data = {type : "enter", memberId : memberid};
		ws.send(JSON.stringify(data));
	}
	
	function receiveMsg(param){
		console.log(param.data); //handleTextMessage 값이 여기로 들어온다!
		 // 받은 json데이터를 js 객체타입으로 변환
        const result = JSON.parse(param.data);
        if(result.type == "myDmCount"){
           $("#dmCount").text(result.dmCount);
           //getReceiveDm();
        }else if(result.ty-e == "sendDmResult"){
           if(result.sendResult== -1){
              alert("쪽지보내기 실패");
           }else{
              closeModal();
              getSendDm();
           }
        }else if(result.type = "readDm"){
        	getSendDm();
        }

	}
	
	function onClose(){
		console.log("쪽지소켓연결완료");
	}
</script>