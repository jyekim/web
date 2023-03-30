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
</head>
<body>
<form id="boardViewForm" action="">
	<input type="text" id ="seq"  name="seq" value="${requestScope.seq }">
	<input type="text" id ="pg"  name="pg" value="${pg }">
	<input type="text" id ="memId" value="${memId }">

<h3></h3>
	<!--whitespace를 잘 적용하려면 width를 넣어줘야함 이게 잡히고 시작되어야함  -->
  <table width="450" border="1" cellpadding="5" cellspacing="0" fram="hsides" rulse="rows">
 <tr>
  	<!--<th>제목</th>  -->
  	<td colspan="3">
  		<h2><span id ="subjectSpan"></span></h2>
  	</td>
  </tr>


 <tr>
  	<td width="150" align="center">글번호 : <span id ="seqSpan"></span></td>
  	<td width="150" align="center">작성자 : <span id ="idSpan"></span></td>
  	<td width="150" align="center">조회수 : <span id ="hitSpan"></span></td>
  </tr>
  
  <tr> <!--pre태그를 쓰면   -->
  	<td colspan="3" height="250" valign="top">
  		<div style="width: 100%; height:100%; overflow:auto;"> <!-- 밑으로 긴 글 일때 스크롤바가 뜨게 -->
  			<pre style="white-space:pre-line; word-break: break-all;">
  				<span id="contentSpan"></span>
  			</pre>
  		</div>
  	</td>
  </tr>
  
  </table>
  		<input type="button" value="목록" onclick="location.href='/miniProject_jQuery/board/boardList.do? pg=${pg}'">
 	
 	
 	
 		<span id="boardViewSpan"> 
 		 	<input type="button" value="글 수정" id="boardUpdateFormBtn">
 		 
 		 	<input type="button" value="글 삭제" id="boardDeleteBtn">
 		</span>
 	
 	
 			<input type="button" value="답글" id="boardReplyFormBtn">
 	</form>
 	

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/boardView.js">

</script>
</body>
</html>