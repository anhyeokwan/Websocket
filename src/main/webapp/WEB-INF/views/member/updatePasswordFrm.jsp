<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<h1>이것이 비밀번호 변경이다.</h1>
	<hr>
	
	
	현재비밀번호 : <input type="password" name="memberPw"><span id="pwChk"></span><br>
	<button type="button" id="pwButton" onclick="pwCheck(${sessionScope.m.memberNo});">조회하기</button>
	
	
	<div style="display: none;">
		<form action="/updatePassword.do" method="post">
			<input type="hidden" name="memberNo" value="${sessionScope.m.memberNo }">
			새 비밀번호 : <input type="password" name="memberPw"><br>
			비밀번호 확인 : <input type="password" name="newPwChk"><span id="pwNewChk"></span><br>
			
			<button type="submit" onclick="newCheck();" id="updateBtn">변경</button>
		
		</form>
	</div>
	
	 
	<script type="text/javascript">
		function pwCheck(memberNo){
			const memberPw = $("[name=memberPw]").eq(0).val();
			$.ajax({
				url : "/checkPw.do",
				data : {
					memberPw : memberPw,
					memberNo : memberNo		
				},
				success : function(data){
					console.log("확인이다 임마");
					if(data == "0"){
						$("#pwChk").text("알맞은 비밀번호입니다.");
						$("div").css("display", "block");
					} else if(data == "1"){
						$("#pwChk").text("알맞지 않은 비밀번호입니다.");
					}
				}
			});
		}
		
		function newCheck(){
			const newPw = $("[name=memberPw]").eq(1).val();
			const newPwChk = $("[name=newPwChk]").val();
			
			if(newPw != newPwChk){
				console.log("틀렸다임마!")
				$("#updateBtn").attr("type", "button")
			}else{
				$("#updateBtn").attr("type", "submit")
			}
		}
		
	</script>
</body>
</html>
















