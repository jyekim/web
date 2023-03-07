<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="member.dao.MemberDAO"
		 import="member.bean.MemberDTO" %>
    
<%	  
request.setCharacterEncoding("UTF-8");//post인 경우에 쓰는것 안에서 내부적으로 페이지 넘겨주는것이기에 request response 다 각각 한글처리해야함
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		

// oracleDB랑 연동 lib에 .jar 저장
		//DB
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setZipcode(zipcode);
		memberDTO.setAddr1(addr1);
		memberDTO.setAddr2(addr2);
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance(); //클래스 생성 
		memberDAO.memberUpdate(memberDTO); // 함수 호출 
						
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>회원정보수정 완료!</h3>
<script  type = "text/javascript">
window.onload = function(){
	alert("회원정보수정 완료!!");
	location.href = "loginForm.jsp";
}
</script>
</body>
</html>