<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이것이 회원정보 수정이다.</h1>
	<hr>
	
	<form action="/updateMember.do" method="post">
		<ul>
			<li>
			  	번호 : <input type="text" name="memberNo" value="${sessionScope.m.memberNo }" disabled="disabled">
			</li>
			<li>
			  	아이디: <input type="text" name="memberId" value="${sessionScope.m.memberId }" readonly="readonly">
			</li>
			<li>
			  	이름 : <input type="text" name="memberName" value="${sessionScope.m.memberName }" disabled="disabled">
			</li>
			<li>
			  	전화번호 : <input type="text" name="phone" value="${sessionScope.m.phone }">
			</li>
			<li>
			  	이메일 : <input type="text" name="email" value="${sessionScope.m.email }">
			</li>
		</ul>
		<input type="submit" value="정보수정">
		<a href="/updatePasswordFrm.do">비밀번호 변경하기</a>
		<a href="/mypage.do">취소</a>
		
	</form>
</body>
</html>