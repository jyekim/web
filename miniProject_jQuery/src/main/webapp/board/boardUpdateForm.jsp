<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#boardUpdateForm div {   
	font-size :8pt;
	color:red;
	font-weight:bold;
}
</style>
</head>
<body>
<form id="boardUpdateForm">  <!-- action="/miniProject_jQuery/board/boardWrite.do" -->
	<input type="hidden" name="seq" value="${seq }">


<h3>글수정</h3>
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
  	 <input type="button" value="글 수정" id="boardUpdateFormBtn">
  	 <input type="reset" value="다시작성" onclick="location.reload()">
  	 
  	</td>
  </tr>
</table>
<br><br>
</form>


<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type: 'post',
		url: '/miniProject_jQuery/board/boardUpdateForm.do',
		data: 'seq=' + ${seq},
		dataType: 'json',
		success: function(data){
			alert(JSON.stringify(data));
			
				$('#subject').val(data.subject);
				$('#content').val(data.content);
			},
			 error: function(err) {
		           console.log(err); // error msg 요청	        	
			}
		});
	
	
	//글수정
	 $('#boardUpdateFormBtn').click(function(){
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
			
			url: '/miniProject_jQuery/board/boardUpdate.do',
			
			//data: 'subject=' + $('#subject').val() +'&content=' +$('#content').val(),
			data: $('#boardUpdateForm').serialize(), //seq, subject, content
			
			success: function(){
				alert("글 수정 완료");
				location.href='/miniProject_jQuery/board/boardList.do?pg=${requestScope.pg}';
			
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
