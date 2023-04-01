<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>
<img src="../image/post.png" width="80" height="80" alt="인스타" 
onclick="location.href='../pindex.jsp'" style="cursor: pointer;"> <!--홈으로 가는 사진과 마찬가지 onclick하면 메인화면으로 넘어감  -->
<form name="ploginForm" method="post" action="plogin.jsp"> <!--밑에 자바스크립트를 실행하면 action으로 넘어와서 로그인화면으로 감  -->
 <table border="1" cellpadding="5" cellspacing="0"> <!--cellpadding 셀과 글자 사이의 간격, cellspacing 셀 간의 간격  -->
   <tr>
  	<th>아이디</th>
  	<td>
  	 <input type="text" name="id" id="id" size="50" placeholder="아이디 입력">
  	 <div id="idDiv"></div>
  	</td>
   </tr>
   <th>비밀번호</th>
   <td>
   <input type="text" name="pwd" id="pwd" size="50" placeholder="비밀번호 입력">
   <div id="pwdDiv"></div>
   </td>
   
   

</body>
</html>