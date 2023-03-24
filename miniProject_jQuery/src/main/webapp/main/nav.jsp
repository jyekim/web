<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>

	<c:if test="${sessionScope.memId == null}">
	<input type="button" value="로그인" 
			onclick="location.href='/miniProject_jQuery/member/loginForm.do'"><br><br>
	<input type="button" value="회원가입"
			onclick="location.href='/miniProject_jQuery/member/writeForm.do'"><br><br>
	</c:if>
	
	<c:if test="${sessionScope.memId != null}">
		<h3>${memId }님이 로그인</h3>
		<input type= "button" value="로그아웃" id="logoutBtn" >
	</c:if>	
</div>

<!--CDN방식-->
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$('#logoutBtn').click(function(){
	$.ajax({
		type: 'post',
		url: '/miniProject_jQuery/member/logout.do',
		
		success: function(){
			alert("로그아웃");
			location.href ='/miniProject_jQuery/index.jsp';/* './index.jsp'; */ /* 절대번지 '/miniProject_jQuery/index.jsp' */
			
		},
		error: function(err){ //에러가 났을때 띄워달라는것
			console.log(err);
		}
	});
	
	
});  //로그아웃버튼을 클릭했을 때
</script>

