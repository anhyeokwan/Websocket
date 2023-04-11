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
	<c:if test="${not empty sessionScope.m }">
		<h3><a href="/photoWriteFrm.do">글쓰기</a></h3>
	</c:if>
	
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>작성자</th>
		</tr>
		
		<c:forEach items="${list }" var="p" varStatus="i">
			<tr>
				<td>${p.photoBoardNo }</td>
				<td><a href="/photoDetail.do?photoBoardNo=${p.photoBoardNo }">${p.photoBoardWriter }</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/">메인페이지</a>
</body>
</html>