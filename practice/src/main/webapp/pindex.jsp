<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<body>
<div id ="header">
		<h1>
		<img src="/practice/image/post.png" width="100" height="100"
		onclick= "location.href='/practice/pindex.jsp'"
		style="cursor:pointer;"> 연습연습
		</h1>
		<%-- <jsp:inclue page ="./main/pmenu.jsp" /> 
		왜 이부분을 추가하면 에러가 뜨는건지 모르겠음!!!!!
		그냥 카피만 했는데 왜죠???!!!!!!!!!!!!!!!!!!!!
	
		--%>

</div>


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