<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<h1>이것이 마이페이지다.</h1>
	<hr>
	
	<ul>
	  <li>
	  	번호 : <input type="text" name="memberNo" value="${sessionScope.m.memberNo }">
	  </li>
	  <li>
	  	아이디: <input type="text" name="memberId" value="${sessionScope.m.memberId }">
	  </li>
	
	  <li>
	  	이름 : <input type="text" name="memberName" value="${sessionScope.m.memberName }">
	  </li>
	  <li>
	  	전화번호 : <input type="text" name="phone" value="${sessionScope.m.phone }">
	  </li>
	  <li>
	  	이메일 : <input type="text" name="email" value="${sessionScope.m.email }">
	  </li>
	</ul>
	
	
	<a href="/updateFrm.do">수정</a>
	<a href="/delete.do?memberNo=${sessionScope.m.memberNo }">삭제</a>
	<a href="/">메인페이지</a>

	<script type="text/javascript">
		
	</script>
</body>
</html>















