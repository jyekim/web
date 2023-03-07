<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "member.dao.MemberDAO" %>       
    
    
    
<%
	//데이터
	//delete를 아이디로 해야하기때문에 비밀번호로 하게 되면 나머지 중복 비번 회원까지 삭제됨
	String pwd = request.getParameter("pwd");//넘어온 데이터
	System.out.println("pwd = "+ pwd);///맨처음에는 pwd가 null값임 
	
	//세션 방법이 두가지가 있음 db를 갔다오거나 아니면 세션의 값을 비교를 하던가 
	String id = (String)session.getAttribute("memId");
	
	//DB
	boolean exist =false;
	if(pwd != null){
		MemberDAO memberDAO = MemberDAO.getInstance();
		 exist = memberDAO.isExistPwd(id, pwd);	
	}	
	
	if(exist) response.sendRedirect("delete.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div#pwdDiv {
	color :red;
	}
</style>
</head>
<body>
<form name="deleteForm" method="post" action="deleteForm.jsp" onsubmit ="return checkValue()">

	
		<div style ="text-align: center;">
			비밀번호 : <input type="password" name="pwd" id="pwd">
			<input type="button" value= "검색" onclick="checkDelete()">
			<br>
			<div id="pwdDiv">
			<%if(pwd != null && !exist){ %>
				비밀번호가 맞지 않습니다.
			<%} %>
			</div>
		</div>
</form>

<script type ="text/javascript">

function checkDelete(){
	document.getElementById("pwdDiv").innerText = "";
	
	if(document.getElementById("pwd").value == "")
		document.getElementById("pwdDiv").innerText = "먼저 비밀번호를 입력하세요"
	else
		document.deleteForm.submit();
		
}
</script>
</body>
</html>