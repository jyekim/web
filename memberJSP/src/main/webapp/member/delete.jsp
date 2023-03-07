<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import= "member.dao.MemberDAO" %>   

<%
	//데이터
	String id = (String)session.getAttribute("memId");//delete를 아이디로 해야하기때문에 비밀번호로 하게 되면 나머지 중복 비번 회원까지 삭제됨
	
	//세션
	session.invalidate(); //모든 세션을 한꺼번에 없애는거 무효화
	
	
	//DB
	MemberDAO memberDAO = MemberDAO.getInstance();
 	memberDAO.memberDelete(id); //is를 붙이면 true false로 비밀번호가 있으면 true, 없으면 false임 
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
window.onload= function() {
	alert("회원탈퇴 완료");
	location.href ="loginForm.jsp"
	}
</script>
</body>
</html>