<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#boardReplyForm: div {
	font-size :8pt;
	color:red;
	font-weight:bold;
}
</style>
</head>
<body>

<!-- //만약에 submit() =>form 안에 데이터만 가져간다 그래서 form 안에 input이 들어가야함  -->

<form id="boardReplyForm"> 
	<input type="text" name="seq" value="${seq }">
	<input type="text" name="pg" value="${pg }">
	
	<h3>답글쓰기</h3>
  	<table border="1" cellpadding="5" cellspacing="0">
 <tr>
  	<th>제목</th>
  	<td>
  	 <input type="text" name="subject" id="subject" size="50" placeholder="제목 입력">
  	 <div id="subjectDiv"></div>
  	</td>
  </tr>


 <tr>
  	<th>내용</th>
  	<td>
  	  <textarea name="content" id="content" cols="50" rows="15"></textarea>
  	<div id="contentDiv"></div>
  	</td>
  </tr>
  
  <tr>
  	<td colspan="2" align="center">
  	 <input type="button" value="답글작성" id="ReplyBtn">
  	 <input type="reset" value="다시작성">
  	 
  	</td>
  </tr>
</table>
<br><br>
</form>


<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#ReplyBtn').click(function(){
		$('#subjectDiv').empty(); //document.getElementById("subjectDiv").innerText == "";
		$('#contentDiv').empty(); //document.getElementById("contentDiv").innerText == "";
		
		
		if($('#subject').val() == '') { //if(document.getElementById("subject").value == ""){
		 $('#subjectDiv').text('제목 입력');
		 $('#subjct').focus();          //document.getElementById("subject").focus();
		}
		
		else if($('#content').val() == '') {  //else if (document.getElementById("content").value == ""){
			$('#contentDiv').text('내용 입력');  //document.getElementById("contentDiv").innerText = "내용 입력";
			$('#content').focus();	
		}
		
		else {
			$.ajax({
				type: 'post',
				
				url: '/miniProject_jQuery/board/boardReply.do',
				
				//data: 'subject=' + $('#subject').val() +'&content=' +$('#content').val(),
				data: $('#boardReplyForm').serialize(), //seq,pg,subject,content넘겨줌
				
				
				success: function(){
					alert("답글 작성 완료");
					location.href='/miniProject_jQuery/board/boardList.do?pg='+${pg}; //boardList에 들어가서 답글이 뿌려지는걸 볼 수 있음
				
				},
			
		        error: function(err) {
		           console.log(err); // error msg 요청
		        }	
			});
		}
		});
	});	


</script>
</body>
</html>