<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
form[name="loginForm"] div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>
<body>

<form name="loginForm" method="post" action="/miniProject_jQuery/member/login.do">


 <table border="1" cellpadding="5" cellspacing="0">
   <tr>
  	<th>아이디</th>
  	<td>
  	 <input type="text" name="id" id="id" >
  	 <div id="idDiv"></div>
  	</td>
   </tr>
  
   <tr>
  	<th>비밀번호</th>
  	<td>
  	 <input type="password" name="pwd" id="pwd" >
  	 <div id="pwdDiv"></div>
  	</td>
   </tr>
  
   <tr>
  	<td colspan="2" align="center">
  	 <input type="button" value="로그인" onclick="checkLogin()">
	 <input type="button" value="회원가입" onclick="location.href='writeForm.jsp'">
  	</td>
  </tr>
 </table>
</form>



<script type="text/javascript">
function checkLogin(){
	document.getElementById("idDiv").innerText = "";
	document.getElementById("pwdDiv").innerText = "";
	
	if(document.getElementById("id").value == "")
		document.getElementById("idDiv").innerText="아이디 입력";
	else if(document.getElementById("pwd").value == "")
		document.getElementById("pwdDiv").innerText="비밀번호 입력";
	else
		document.loginForm.submit();
}
</script>
</body>
</html> --%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
form[name="loginForm"] div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>
<body>

<form name="loginForm" id="loginForm">


 <table border="1" cellpadding="5" cellspacing="0">
   <tr>
  	<th>아이디</th>
  	<td>
  	 <input type="text" name="id" id="id" >
  	 <div id="idDiv"></div>
  	</td>
   </tr>
  
   <tr>
  	<th>비밀번호</th>
  	<td>
  	 <input type="password" name="pwd" id="pwd" >
  	 <div id="pwdDiv"></div>
  	</td>
   </tr>
  
   <tr>
  	<td colspan="2" align="center">
  	 <input type="button" value="로그인" id="loginBtn"> <!--id속성은 유일하게 하나일떄 class는 여러개   -->
	 <input type="button" value="회원가입" onclick="location.href='writeForm.jsp'">
  	</td>
  </tr>
 </table>
 
 <br><br>
 
 <div id="loginResult"></div> <!-- 아이디랑 비번 틀렸다는걸 여기에 띄우기위해 -->
</form>


<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script> <!--jquery 쓰는 이유 화면 이동 안하기 위해서  -->
<script type="text/javascript">
$('#loginBtn').click(function(){ /*로그인 화면에서 로그인버튼 클릭했을때  alert 알림 띄우는것   */
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	
	if( $('#id').val() == '' ) {
		$('#idDiv').text('아이디 입력');
		$('#id').focus(); 
	}
	else if($('#pwd').val() == '') {
		$('#pwdDiv').text('비밀번호 입력');
		$('#pwd').focus(); 
	}
	else{
		$.ajax({
			type:'post',//여기는 'get' or 'post'방식 택
			
			url: '/miniProject_jQuery/member/login.do',
			
			data: 'id='+$('#id').val()+'&pwd='+$('#pwd').val(),//변수=값$변수서버로 보낼 데이터, id, pwd  
			
			dataType: 'text', //서버로부터 받는 자료형, 문자열(text), xml, html, json
			
			success: function(data){
				data = data.trim();
				
				if(data == 'ok'){
					location.href = '../index.jsp';
				}else if(data == 'fail'){
					$('#loginResult').text('아이디 또는 비밀번호가 맞지 않습니다');
					$('#loginResult').css('font-size', '12pt');
				}
			},
			
		    error: function(err){
		         console.log(err);
		      }
			
		});
	}
	
}); /* 코드는 항상 중괄호 안에 넣기 */
</script>

</body>
</html>






