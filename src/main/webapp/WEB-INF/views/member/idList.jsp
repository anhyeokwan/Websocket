<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이것이 전체 아이디 목록이다.</h1>
	<hr>
	
	<form action="/searchMember3.do" method="post">
		<c:forEach items="${list }" var="memberId">
			<input type="checkbox" name="memberId" value="${memberId }">
			${memberId }
		</c:forEach>
		<input type="submit" value="조회">
	</form>
	<a href="/">메인페이지로</a>
</body>
</html>