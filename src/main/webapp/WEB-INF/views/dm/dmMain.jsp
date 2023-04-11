<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<link rel="stylesheet" href="/resources/css/dm.css">
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript" src="/resources/js/dm.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<h1>이것이 내 쪽지함이다.</h1>
	<hr>
	
	<h3>쪽지보내기</h3>
	<button onclick="sendDmModal();">쪽지보내기</button>
	<p>받는사람, 내용 입력해서 전송하는 경우 ajax로 dm테이블에  insert</p>
	<!-- <div class="sendDm">
		받는사람 : <input type="text" name="receiver"><br>
		내용 : <textarea name="dmContent"></textarea>
		<button onclick="insertDm('${sessionScope.m.memberId}');">보내기</button>
	</div> -->
	<hr>
	
	<h3>받은 쪽지함</h3>
	<!-- <button onclick="getReceiveDm();">받은 쪽지 가져오기</button> -->
	<table border="1" class="receiverDmTbl">
		<thead>
			<tr>
				<th>보낸사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음 여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<hr>
	
	<h3>보낸 쪽지함</h3>
	<!-- <button onclick="getSendDm('${sessionScope.m.memberId}');">보낸 쪽지 가져오기</button> -->
	<table border="1" class="sendDmTbl">
		<thead>
			<tr>
				<th>받은사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음 여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	<!-- 쪽지보내기 모달 -->
	<div id="sendDm-modal" class="modal-wrapper">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지 보내기</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="sendDmFrm">
					<label>수신자 : </label>
					<select name="receiver">
						
					</select>
					<textarea name="dmContent"></textarea>
					<input type="hidden" id="sender" value="${sessionScope.m.memberId }">
					<button onclick="dmSend();">쪽지보내기</button>
					<button onclick="closeModal();">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 쪽지상세보기 모달 -->
	<div id="dmDetail" class="modal-wrapper">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지 내용</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="DmFrm">
					<div>
						<span>발신자 : </span>
						<span id="detailSender"></span>
					</div>
					
					<div>
						<span>날짜 : </span>
						<span id="detailDate"></span>
					</div>
					
					<div>
						<button onclick="replyDm();">답장</button>
						<button onclick="closeDetail();">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
	
		function insertDm(memberId){
			const receiver = $("[name=receiver]").val();
			const dmContent = $("[name=dmContent]").val();
			
			$.ajax({
				url : "/insertDm.do",
				type : "post",
				data : {
					receiver : receiver,
					dmContent : dmContent,
					sender : memberId
				},
				success : function(data){
					console.log(data)
				}
			})
		}
		
		// 화면들어오면 바로 보여주기
		$(function(){
			getReceiveDm();
			getSendDm();
		})
	</script>
</body>
</html>























