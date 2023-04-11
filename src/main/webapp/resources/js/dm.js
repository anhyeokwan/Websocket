function sendDmModal(){
	$.ajax({
		url : "selectAllmemberId.do",
		success : function(list){
			$("select[name=receiver]").empty();
			for(let i = 0; i < list.length; i++){
				const option = $("<option>");
				option.val(list[i]);
				option.text(list[i]);
				$("[name=receiver]").append(option);
			}
		}
	});
	$("#sendDm-modal").css("display", "flex");
}	

function closeModal(){
	$("#sendDm-modal").hide();
	$("textarea[name=dmContent]").val("");
}

function dmSend(){
	const receiver = $("select[name=receiver]").val();
	const dmContent = $("textarea[name=dmContent]").val();
	const sender = $("#sender").val();
	
	$.ajax({
		url : "/insertDm.do",
		type : "post",
		data : {
			receiver : receiver,
			dmContent : dmContent,
			sender : sender
		},
		success : function(data){
			console.log(data)
			if(data == 1){
				closeModal();
				getSendDm()
			}else{
				alert("쪽지보내기 시파이다!");
			}
			
		}
	})
}

function getSendDm(){
	const sender = $("#sender").val();
	$.ajax({
		url : "/selectAllSend.do",
		data : { sender : sender },
		success : function(data){
			console.log(data);
			const tbody = $(".sendDmTbl>tbody");
			for(let i = 0; i < data.length; i++){
				const tr = $("<tr>");
				tr.append("<td>" + data[i].receiver + "</td>");
				tr.append("<td onclick='dmDtail(" + data[i].dmNo + ")'>" + data[i].dmContent + "</td>");
				tr.append("<td>" + data[i].dmDate + "</td>");
				if(data[i].readCheck == 0){
					tr.append("<td>읽지 않음</td>");
				}else if(data[i].readCheck == 1){
					tr.append("<td>읽음</td>");
				}
				tbody.append(tr);
			}
			
		}
	});
}

function closeDetail(){
	$("#dmDetail").hide();
}

function getReceiveDm(){
	const receiver = $("#sender").val();
	$.ajax({
		url : "/selectAllSend.do",
		data : { receiver : receiver },
		success : function(data){
			console.log(data);
			const tbody = $(".receiverDmTbl>tbody");
			for(let i = 0; i < data.length; i++){
				const tr = $("<tr>");
				tr.append("<td>" + data[i].sender + "</td>");
				tr.append("<td onclick='dmDtail(" + data[i].dmNo + ")'>" + data[i].dmContent + "</td>");
				tr.append("<td>" + data[i].dmDate + "</td>");
				if(data[i].readCheck == 0){
					tr.append("<td class='readChk'>읽지 않음</td>");
				}else if(data[i].readCheck == 1){
					tr.append("<td>읽음</td>");
				}
				tbody.append(tr);
			}
			
		}
	});
}

function dmDtail(dmNo){
	const idx = $(".readChk").index(this);
	console.log("클릭한 인덱스 : " + idx);
	console.log($(".readChk").eq(idx).text());
	$(".readChk").eq(idx).text("읽음");
	$.ajax({
		url : "/dmDetail.do",
		data : {dmNo : dmNo},
		success : function(data){
			$("#detailSender").text(data.sender);
			$("#detailDate").text(data.dmDate);
			$("#detailContent").text(data.dmContent);
			
			$("#dmDetail").css("display","flex");
			// 상세보기 화면 표시후 웹소켓에 전달
			const wsData = {type : "readCheck",
							sender : data.sender,
							receiver : data.receiver
							}
			ws.send(JSON.stringify(wsData));
		}
	})
	
}




























