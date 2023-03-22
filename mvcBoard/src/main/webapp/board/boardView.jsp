<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="board.dao.BoardDAO" %>
<%@ page import ="board.bean.BoardDTO" %>
<%@ page import ="board.bean.BoardPaging" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import ="java.util.List" %>


<%
		//데이터
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		//db
		BoardDAO boardDAO = BoardDAO.getInstance();
		BoardDTO boardDTO = boardDAO.getBoard(seq);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	font-size :8pt;
	color:red;
	font-weight:bold;
}
</style>
</head>
<body>
<form name="boardWriteForm" method="post" action="boardWrite.jsp">


<h3><img src="../image/img1.jpg" width="70" height="70" alt="고양" 
onclick="location.href='../index.jsp'" style="cursor: pointer;"> 글쓰기

	<!--whitespace를 잘 적용하려면 width를 넣어줘야함 이게 잡히고 시작되어야함  -->
  <table width="450" border="1" cellpadding="5" cellspacing="0" fram="hsides" rulse="rows">
 <tr>
  	<!--<th>제목</th>  -->
  	<td colspan="3">
  		<h2><%=boardDTO.getSubject()%></h2>
  	</td>
  </tr>


 <tr>
  	<td width="150" align="center">글번호 :<%=boardDTO.getSeq() %> </td>
  	<td width="150" align="center">작성자 :<%=boardDTO.getId() %> </td>
  	<td width="150" align="center">조회수 :<%=boardDTO.getHit() %></td>
  </tr>
  
  <tr> <!--pre태그를 쓰면   -->
  	<td colspan="3" height="250" valign="top">
  		<div style="width: 100%; height:100%; overflow:auto;"> <!-- 밑으로 긴 글 일때 스크롤바가 뜨게 -->
  		<pre style="white-space:pre-line; word-bradk: break-all;">
  		<%=boardDTO.getContent() %></pre>
  		</div>
  		</td>
  	</tr>
  	 </table>
  		<input type="button" value="목록" onclick="history.go(-1)">
 	</form>

</body>
</html>