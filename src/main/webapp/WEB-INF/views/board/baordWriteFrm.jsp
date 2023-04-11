<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style type="text/css">
	.fileZone{
		width:300px;
		height : 200px;
		box-sizing: border-box;
		display : flex;
		flex-wrap: wrap;
		border : 2px dotted #0b85a1;
		align-content : flex-start;
	}
	
	.fileMsg{
		color: #0b85a1;
		font-size: 13px;
		width: 100%;
		text-align: center;
		align-self: center;
		line-height: 200px;
	}
	
	.fileName{
		width: 100%;
		position: relative;
		height: 18px;
	}
	
	.fileName>span{
		font-size: 16px;
		line-height: 18px;
	}
	
	.closeBtn{
		position: absolute;
		right: 10px;
		cursor: pointer;
	}
	
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
	
	.photo>div>label{
		width: 70px;
		height: 40px;
		border: 1px solid black;
	}
	
	.photo>div:nth-child(2){
		margin-left: 20px;
		margin-right: 10px;
	}
	
	.photo>div:nth-child(3){
		margin-left: 10px;
		margin-right: 20px;
	}
	
</style>
</head>
<body>
	<h1>이것이 게시판 글쓰기다.</h1>
	<hr>
	
	<form action="/boardWrite.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td><input type="text" name="boardWriter" value="${sessionScope.m.memberId }"></td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td>
					<div class="fileZone">
						<div class="fileMsg">파일을 여기에 올려두세요 뀨</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<th colspan="2">
					이미지
				</th>
			</tr>
			
			<tr>
				<td colspan="2">
					<div class="photo">
						<div class="president">
                        	<label for="present-photo">대표사진</label>
                        	<input type="file" id="present-photo" name="filename" style="display: none">
                        	<img id="present-img">
                        </div>
                       <div class="crew">
                        	<label for="plus-photo1">추가사진</label>
                        	<input type="file" id="plus-photo1" name="filename" style="display: none">
                        	<img id="crew-img">
                       </div>
                       <div class="crew">
                        	<label for="plus-photo2">추가사진</label>
                        	<input type="file" id="plus-photo2" name="filename" style="display: none">
                        	<img id="crew-img">
                       </div>
                       <div class="crew">
                        	<label for="plus-photo3">추가사진</label>
                        	<input type="file" id="plus-photo3" name="filename" style="display: none">
                        	<img id="crew-img">
                       </div>
					</div>
				</td>
			</tr>
			
			<tr>
				<th>글쓰기</th>
				<td>
					<textarea name="boardContent"></textarea>
				</td>
			</tr>
			
			<tr>
				
				<td colspan="3">
					<input type="file" name="boardfile" multiple="multiple" style="display: none;">
					<input type="submit" value="등록">
					<a href="/">메인페이지로</a>
				</td>
			</tr>
		</table>
	</form>
	
	<script type="text/javascript">
		const fileZone = $(".fileZone");
		const files = new Array();
		// 드래그영역에 들어올 때
		fileZone.on("dragenter", function(e){
			e.stopPropagation(); // 이벤트버블링 제거
			e.preventDefault();	// 기본이벤트 제거
			$(this).css("border", "2px dashed #0b85a1");
		});
		
		// 드래그 영역에서 나갈때
		fileZone.on("dragleave", function(e){
			e.stopPropagation(); // 이벤트버블링 제거
			e.preventDefault();	// 기본이벤트 제거
			$(this).css("border", "2px dotted #0b85a1");
		});
		
		// 드래그 영역에 올라와있을 때
		fileZone.on("dragover", function(e){
			e.stopPropagation(); // 이벤트버블링 제거
			e.preventDefault();	// 기본이벤트 제거
		});
		
		// 드래그영역에 내려놓을때
		fileZone.on("drop", function(e){
			e.stopPropagation(); // 이벤트버블링 제거
			e.preventDefault();	// 기본이벤트 제거
			// e.originalEvent.dataTransfer.files
			for(let i = 0; i < e.originalEvent.dataTransfer.files.length; i++){
				files.push(e.originalEvent.dataTransfer.files[i]);
				/*
				<div class="fileName">
					<span>업로드한 파일명</span>
					<sapn>X</span>
				</div>
				*/
				$(".fileMsg").hide();
				$(this).find(".fileName").remove();
				for(let i = 0; i < files.length; i++){
					const fileNameDiv = $("<div>");
					fileNameDiv.addClass("fileName");
					const fileNameSpan = $("<span>")
					fileNameSpan.text(files[i].name);
					const closeBtn = $("<span>");
					closeBtn.addClass("closeBtn");
					closeBtn.text("X");
					
					closeBtn.attr("onclick", "deleteFile(this)");
					
					fileNameDiv.append(fileNameSpan).append(closeBtn);
					$(this).append(fileNameDiv);
				}
			}
			fileSetting();
		
		});
		
		function deleteFile(obj){
			const deleteFilename = $(obj).prev().text();
			for(let i = 0; i < files.length; i++){
				if(files[i].name == deleteFilename){
					files.splice(i, 1);
					break;
				}
			}
			if(files.length == 0){
				$(".fileMsg").show();
				fileZone.css("border", "2px dotted #0b85a1");
			}
			$(obj).parent().remove();
			fileSetting();
		}
		
		function fileSetting(){
			// input[type=file] value는 보안상 변경이 불가능
			// input[type=file] 변경용 객체
			const dataTransfer = new DataTransfer();
			for(let i = 0; i < files.length; i++){
				dataTransfer.items.add(files[i]);
			}
			$("input[name=boardfile]").prop("files", dataTransfer.files);
		}
	</script>
	
</body>
</html>
























