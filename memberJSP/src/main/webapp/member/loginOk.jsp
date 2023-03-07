<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//데이터
	String  name = null;
	String id = null;
	
	
	//쿠키
	/*
	Cookie[] ar = request.getCookies();//특정쿠키를 얻을 수가 없으므로 모든 쿠키를 다 가져옴/ 배열형태로 다 가져옴
	if(ar != null) {
		for(int i=0; i<ar.length; i++){
			String cookieName = ar[i].getName();
			String cookieValue = ar[i].getValue();
			
			System.out.println("쿠키명 = " + cookieName);
			System.out.println("쿠키명 = " + cookieValue);
			System.out.println();
			
			if(cookieName.equals("memName")) name = cookieValue;
			if(cookieName.equals("memId")) id = cookieValue;
			
		}//for
	}//if
	*/
	
	
	
	//세션
	name = (String)session.getAttribute("memName");  //자식 클래스로 형변환 시켜줘야함 
	id = (String)session.getAttribute("memId");  
				
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3><%=name %>님 로그인</h3>
	<br>
	<input type="button" value="회원정보수정" onclick="location.href='updateForm.jsp'">
	<input type="button" value="로그아웃" onclick="location.href='logout.jsp'">
	<input type="button" value="회원탈퇴" onclick="location.href='deleteForm.jsp'">
</body>
</html>