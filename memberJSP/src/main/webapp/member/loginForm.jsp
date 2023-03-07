<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>
<body>
<form name="loginForm" method="post" action="login.jsp">
 <table border="1" cellpadding="5" cellspacing="0">
   <tr>
  	<th>아이디</th>
  	<td>
  	 <input type="text" name="id" id="id" size="30" placeholder="아이디 입력">
  	 <div id="idDiv"></div>
  	</td>
   </tr>
  
   <tr>
  	<th>비밀번호</th>
  	<td>
  	 <input type="password" name="pwd" id="pwd" size="40" >
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
</html>