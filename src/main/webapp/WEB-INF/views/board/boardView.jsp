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
	<h1>이것이 게시판뷰다.</h1>
	<hr>
	
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${b.boardNo }<td>
			<th>작성일</th>
			<td>${b.boardDate }</td>
		</tr>
		
		<tr>
			<th>첨부파일</th>
			<td colspan="3">
				<c:forEach items="${b.fileList }" var="f">
					<p>
						<a href="#">${f.filename }</a>
					</p>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				${b.boardContent }
			</td>
		</tr>
	</table>
</body>
</html>