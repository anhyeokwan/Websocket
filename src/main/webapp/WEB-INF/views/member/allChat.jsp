<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style type="text/css">
	.chatting{
		width: 500px;
		display : none;
	}
	
	.messageArea{
		overflow-y: auto;
		border : 1px solid black;
		height : 500px;
		display : flex;
		flex-direction: column;
		background-color: #b2c7d9;
	}
	
	.messageArea>p{
		text-align: center;
		width : 100%;
	}
	
	#sendMsg{
		width : 75%;
	}
	
	#sendBtn{
		width : 20%;
	}
	
	.chat{
		margin-bottom : 10px;
		padding : 8px;
		border-radius: 3px;
	}
	
	.left{
		position: relative;
		max-width: 300px;
		align-self: flex-start;
		background-color: #fff;
		-webkit-border-radius: 10px;
		-moz-border-radius : 10px;
		border-radius: 10px;
		margin-left : 16px;
		padding : 15px;
	}
	
	.left:after{
		content : '';
		position: absolute;
		border-style: solid;
		border-width : 15px 15px 15px 0px;
		border-color: transparent #fff;
		display: block;
		width : 0;
		z-index : 1;
		left : -15px;
		top : 12px;
	}
	
	.right{
		position: relative;
		max-width: 300px;
		align-self: flex-end;
		background-color: #ffeb33;
		-webkit-border-radius: 10px;
		-moz-border-radius : 10px;
		border-radius: 10px;
		margin-right : 16px;
		padding : 15px;
	}
	
	.right:after{
		content : '';
		position: absolute;
		border-style: solid;
		border-width : 15px 0px 15px 15px;
		border-color: transparent #ffeb33;
		display: block;
		width : 0;
		z-index : 1;
		right : -15px;
		top : 12px;
	}
</style>
</head>
<body>
	<h1>이것이 전체 채팅이다.</h1>
	<hr>
	
	<button onclick="initChat('${sessionScope.m.memberId}');">채팅 시작하기</button>
	<div class="chatting">
		<div class="messageArea"></div>
		<div class="sendBox">
			<input type="text" id="sendMsg">
			<button id="sendBtn" onclick="sendMsg();">전송</button>
		</div>
	</div>
	
	<script type="text/javascript">
		let ws; //웹소켓 객체를 저장할 변수
		let memberId// 회원 아이디 저장용변수
		
		function initChat(param){
			memberId = param;
			// 웹소켓 연결 시도
			ws = new WebSocket("ws://192.168.219.102/chat.do");
			
			// 웹소켓 연결 성공 시 실행할  함수 지정
			ws.onopen = startChat;
			ws.onmessage = receiveMsg;
			ws.onclose = endChat;
			$(".chatting").css("display", "block");
		}
		
		function startChat(){
			console.log("웹소켓 연결완료");
			const data = {type : "enter", msg:memberId}
			ws.send(JSON.stringify(data));
			appendChat("<p>채팅방에 입장했습니다.</p>");
		}
		
		function receiveMsg(param){ // 서버가 받은 데이터가 매개변수로 들어온다.
			console.log(param);
			appendChat(param.data);
		
		};
		function endChat(){
			console.log("웹소켓 연결종료");
			
		}
		
		function sendMsg(){
			const msg = $("#sendMsg").val();
			if(msg != ""){
				// 서버에 소켓으로 문자열 전송
				const data = {type : "chat", msg : msg};
				ws.send(JSON.stringify(data)); 
				appendChat("<div class='chat right'>" + msg + "</div>");
				$("#sendMsg").val("");
			}
		}
		
		function appendChat(msg){
			$(".messageArea").append(msg);
			$(".messageArea").scrollTop($(".messageArea")[0].scrollHeight);
		}
		
		$("#sendMsg").on("keyup", function(key){
			if(key.keyCode == 13){
				sendMsg();
			}
		}) // 가보자고
		
	</script>
</body>
</html>




















