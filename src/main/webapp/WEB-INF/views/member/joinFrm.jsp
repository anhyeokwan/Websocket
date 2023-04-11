<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<body>
	<h1>이것이 회원가입이다.</h1>
	<hr>
	
	<form action="/join.do" method="post">
		<fieldset>
			<legend>회원가입</legend>
			아이디 : <input type="text" name="memberId"><span id=idCheck></span><br>
			비밀번호 : <input type="password" name="memberPw"><br>
			이름 : <input type="text" name="memberName"><br>
			전화번호 : <input type="text" name="phone"><br>
			이메일 : <input type="text" name="email"><br>
			<input type="submit" value="회원가입">
			<a href="/">메인으로 돌아가기</a>
		</fieldset>
	</form>
	
	<script>
		$("[name=memberId").on("change", function(){
			const memberId = $(this).val();
			$.ajax({
				url : "/idcheck.do",
				data : {memberId : memberId},
			success : function(data){
				console.log(data);
				if(data == "0"){
					$("#idCheck").text("사용가능한 아이디입니다.");
					$("#idCheck").css("color", "blue");
				}else{
					$("#idCheck").text("이미 사용중인 아이디입니다.");
					$("#idCheck").css("color", "red");
				}
			}
			});
		});
	</script>
</body>
</html>






















