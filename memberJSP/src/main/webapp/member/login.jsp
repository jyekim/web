<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "member.dao.MemberDAO" %>
<%
		//데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance(); //클래스 생성 
		String name = memberDAO.memberLogin(id, pwd); // 함수 호출

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(name == null) {
	//페이지 이동해야함
	response.sendRedirect("loginFail.jsp");
	
} else {
	//쿠키
	/*
	Cookie cookie = new Cookie("memName", name);
	cookie.setMaxAge(30 * 60);//초 단위 3초가 지나면 없어짐 쿠키값 30분 동안 살아있음 
 	response.addCookie(cookie);//클라이언트에 보내기
	
	
	Cookie cookie2 = new Cookie("memId", id);
	cookie2.setMaxAge(3);//초 단위 3초가 지나면 없어짐
	response.addCookie(cookie2);//클라이언트에 보내기
	*/
	//세션 쿠키보다 세션을 더 많이 씀....
	//HttpSession session = request.getSession(); -  jsp는 이미 내장객체로 세션이 생성되어 있다 그래서 따로 생성할 필요가 없음
	session.setAttribute("memName", name);
	session.setAttribute("memId", id);
	
	//페이지 이동
	response.sendRedirect("loginOk.jsp");
} %>
</body>
</html>