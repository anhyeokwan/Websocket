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
	<h1>05_Spring_WebSocket</h1>
	<hr>
	
	<c:choose>
		<c:when test="${empty sessionScope.m }">
			<form action="/login.do" method="post">
				<fieldset>
					<legend>로그인</legend>
					아이디 : <input type="text" name="memberId">
					비밀번호 : <input type="password" name="memberPw">
					
					<input type="submit" value="로그인">
				</fieldset>
			</form>
			<a href="/joinFrm.do">회원가입</a>
			<form action="/searchMemberId.do" method="post">
				아이디 : <input type="text" name="memberId">
				<input type="submit" value="조회">
			</form>
			
			<button type="button" onclick="findBtn();">비밀번호 찾기</button>
			
			<div style="display: none;">
				<span id="chk"></span><br>
				아이디 : <input type="text" name="memberId"><br>
				이메일 : <input type="text" name="email"><br>
				<button type="button" id="emailBtn" onclick="chkIdEmail();">확인하기</button>
			</div>q
			<br>
			<div class="authEmail" style="display: none;">
				<input type="text" name="authEmail"><span id="emailChk"></span><br>
				<button type="button" onclick="sendEmail();">인증번호 받기</button>
				<button type="button" onclick="authBtn();">인증하기</button>
			</div>
		</c:when>
		
		<c:otherwise>
			<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
			<h2>[${sessionScope.m.memberName }]님 환영합니다.</h2>
			<h3><a href="/allMemberChat.do">전체채팅</a></h3>
			<h3><a href="/dmMain.do">쪽지함가기</a></h3>
			<h3><a href="/boardList.do?reqPage=1">게시판</a></h3>
			<a href="/logOut.do">로그아웃</a>
			<h3><a href="/selectAllMember.do">전체회원조회</a></h3>
			
			<h3><a href="/mypage.do">마이페이지</a></h3>
			<form action="/searchMemberName.do" method="post">
				이름 : <input type="text" name="memberName">
				<input type="submit" value="조회">
			</form>
			
			<form action="/searchMember1.do" method="post">
				<select name="type">
					<option value="id">아이디</option>
					<option value="name">이름</option>
				</select>
				<input type="text" name="keyword">
				<input type="submit" value="검색">
			</form>
			
			<h3>아이디 or 이름으로 검색</h3>
			<p>
			아이디만 입력하고 검색하는 경우 아이디로 조회,<!-- memberId = 입력한 값, memberName = "" -->
			이름만 입력하고 검색하는 경우 이름으로 조회, <!-- memberId = "", memberName = 입력한 값 -->
			둘다 입력하고 검색하는 경우 두개 and로 조회	<!-- memberId = 입력한 값, memberName = 입력한 값 -->
			</p>
			<form action="/searchMember2.do" method="post">
				아이디 : <input type="text" name="memberId"><br>
				이름 : <input type="text" name="memberName"><br>
				<input type="submit" value="검색">
			</form>
			<h3><a href="/idList.do">전체회원 아이디 목록</a></h3>
			<h3><a href="/searchMember4.do">회원조회4</a></h3>
			
			<form action="/insertMajor.do" method="post">
				<input type="hidden" name="memberNo" value="${sessionScope.m.memberNo }">
				<div class="check-test">
					<label for="dkssud">안녕</label>
					<input type="text" id="dkssud" name="memberMajor">
				
					<label for="hi">hi</label>
					<input type="text" id="hi" name="memberMajor">
				
					<label for="hello">hello</label>
					<input type="text" id="hello" name="memberMajor">
				
					<label for="nihon">곤니치와</label>
					<input type="text" id="nihon" name="memberMajor">
				</div>
				
				<button>인서트</button>
			</form>
			
			<a href="/photoList.do">사진실험</a>
		</c:otherwise>
	</c:choose>
	<button id="allMemberAjax">전체회원조회(ajax)</button>
	<div id="ajaxResult"></div>
	
	<script type="text/javascript">
		let mailCode;
		function sendEmail(){
			const email = $("[name=email]").val();
			$.ajax({
				url : "/sendMail.do",
				type : "post",
				data : {
					email : email
				},
				success : function(data){
					console.log(data)
					mailCode = data;
				}
			})
		}
		
		function authBtn(){
			const authEmail = $("[name=authEmail]").val();
			if(mailCode != null){
				if(authEmail == mailCode){
					console.log("성공이다!");
					$("#emailChk").text("인증성공");
					const go = $("<a href='/updatePwFrm.do'>변경하러 가기</a>");
					$(".authEmail").append(go);
				}else{
					console.log("실패인가");
					$("#emailChk").text("인증실패");
				}
			}
		}
		
	
		$("#allMemberAjax").on("click", function(){
			$.ajax({
				url : "/ajaxAllMember.do",
				success : function(data){
					console.log(data);
					const table = $("<table>")
					const titleTr = $("<tr>")
					titleTr .html("<th>번호</th><th>아이디</th><th>이름</th><th>전화번호</th>");
					table.append(titleTr);
					for(let i = 0; i < data.length; i++){
						const tr = $("<tr>")
						tr.append("<td>" + data[i].memberNo + "</td>");
						tr.append("<td>" + data[i].memberId + "</td>");
						tr.append("<td>" + data[i].memberName + "</td>");
						tr.append("<td>" + data[i].phone + "</td>");
						table.append(tr);
					}
					$("#ajaxResult").html(table);
				}
			})
		})
		
		function findBtn(){
			$("div").eq(0).css("display", "block");
		}
		
		function chkIdEmail(){
			const memberId = $("[name=memberId]").eq(2).val();
			const email = $("[name=email]").val();
			
			$.ajax({
				url : "/checkIdEmail.do",
				data : {
					memberId : memberId,
					email : email
				},
				success : function(data){
					console.log(data);
					if(data == "0"){
						$("#chk").text("아이디 또는 이메일이 잘못되었습니다.");
						$("#emailBtn").attr("type", "button");
						$("#authEmail").css("display", "none");
					}else if(data == "1"){
						$("#emailBtn").attr("type", "submit");
						$(".authEmail").css("display", "block");
					}
				}
			})
		}
	</script>
	
</body>
</html>
















