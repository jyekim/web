<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a:link { color : black; text-decoration:none;}
a:visited {color : black; text-decoration:none;}
a:hover {color : purple; text-decoration: underline;} 
a:active{color : pink; text-decoration:none;}
</style>
</head>
<div>
<body>
<div class="container">
	<div class="sinin-container"></div>
		<h2>****메인 화면 ****</h2>
	<form class="form-container" action="sign-in" method="post">
	<input type="text" classname="username" name="username"  placeholder="아이디입력" autocomplete="off"/>
	<input type="password" classname="pwd" name="pwd" placeholder="비밀번호입력"autocomplete="off">
	<a href="forget-password" class="help"> </a>


<%-- <% if(session.getAttribute("memId") == null) {%>
	<a href=""> 회원가입</a><br>
	<a href=""> 로그인</a><br>
	
<%}else {%> 
	<a href=""> 로그아웃</a><br>
	<a href=""> 회원정보수정</a><br>
	<a href=""> 회원탈퇴</a><br>
	<a href=""> 글쓰기</a><br>
<%}%>

<a href=""> 목록</a><br>  --%>


</body>
</html>