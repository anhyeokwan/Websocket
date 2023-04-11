<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.photo{
		display: flex;
		justify-content: center;
	}
	
	.photo>div{
		width: 150px;
		height: 150px;
		border: 1px solid black;
		line-height: 150px;
		text-align: center;
	}
	
	
	
	.photo>div:nth-child(2){
		margin-left: 20px;
		margin-right: 10px;
	}
	
	.photo>div:nth-child(3){
		margin-left: 10px;
		margin-right: 20px;
	}
	
	.fileDiv{
		position: relative;
		
	}
	
	.delFile{
		position: absolute;
		bottom: 0px;
		right: 0px;
		font-size: 15px;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<h1>이것이 사진 글쓰기다.</h1>
	<hr>
	
	<form action="/photoWrite.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>
					${sessionScope.m.memberName }
					<input type="hidden" name="photoBoardWriter" value="${sessionScope.m.memberId }">
				</td>
			</tr>
			
			<tr>
				<th colspan="2">
					사진등록
				</th>
			</tr>
			
			<tr>
				<td colspan="2">
					<div class="photo">
						<div class="president">
                        	<label for="present-photo">대표사진</label>
                        	<input type="file" class="present-photo" id="present-photo" accept=".jpeg, .png, .jpg" onchange="imgUpload(this);" multiple="multiple" name="imgFile" style="display: none">
                        </div>
                       
					</div>
				</td>
			</tr>
			
			<tr>
				<th colspan="2">글쓰기</th>
			</tr>
			
			<tr>
				<td colspan="2">
					<div class="mb-3">
					  <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
					  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="photoBoardContent"></textarea>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align: center;">
					<button type="submit">등록하기</button>
				</td>
			</tr>
		</table>
	</form>
	
	<script type="text/javascript">
	
		const arr = new Array();	
		
		function imgUpload(f){
			const fileUp = $("#present-photo")[0].files;
			
			if(fileUp.length > 5){
				alert("5개까지만 선택할 수 있다!");
			}else{
				$(".president").hide();
				for(let i = 0; i < fileUp.length; i++){
					const reader = new FileReader(); // 파일 정보를 읽어오는 객체
					arr.push(fileUp[i]);
					console.log(fileUp[i].name);
					const div = $("<div class='fileDiv'>");
					if(arr.length > 5){
						alert("파일은 5개까지만 올릴 수 있습니다.");
						arr.pop();
						break;
					}
					
					reader.onload = function(e){
						div.append("<img class='imgClass' src='" + e.target.result + "'>");
						$(".imgClass").css("width", "140px").css("height", "140px");
						div.append("<span class='delFile' onclick='delFile(this)'>x</span>")
					}
					reader.readAsDataURL(arr[i]);

					$(".photo").append(div);
					
				}
				
			}
			
		}
		
		function delFile(obj){
			
			console.log(obj);
			arr.splice(obj, 1);
			
			const delFile = $(obj).parent();
			delFile.remove();
			
			console.log(arr.length);
			
			if(arr.length == 0){
				$(".president").show();
			}
			
		}
		
	</script>
</body>
</html>























