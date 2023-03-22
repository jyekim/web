<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="board.dao.BoardDAO" %>
<%@ page import ="board.bean.BoardDTO" %>
<%@ page import ="board.bean.BoardPaging" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import ="java.util.HashMap" %>
<%@ page import ="java.util.List" %>
<%@ page import ="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- <% 		//데이터 
		int pg = Integer.parseInt(request.getParameter("pg"));
		

		//세션
		String memId =(String)session.getAttribute("memId");

		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		
		//한페이지당 5개씩 게시물 끊기
		/*     startNum   endNum
		pg=1 rn>=1 and rn<=5
		pg=2 rn>=6 and rn<=10
		pg=3 rn>=11 and rn<=15
		*/
		int endNum = pg *5;
		int startNum =endNum -4;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardDTO> list = boardDAO.boardList(map);
		
		
		
		//페이징 처리 
		
		int totalA = boardDAO.getTotalA();//총글수
		
		BoardPaging boardPaging = new BoardPaging();
		boardPaging.setCurrentPage(pg);
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(5);
		boardPaging.setTotalA(totalA);
		
		boardPaging.makePagingHTML();
	
	
 %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type = "text/css">
.subjectA:link{color : black; text-decoration:none;}
.subjectA:visited {color : black; text-decoration:none;}
td .subjectA:hover {color : cyan; text-decoration: underline;} 
.subjectA:active{color : black; text-decoration:none;}

#currentPaging{
	color: red;
	border:1px solid red;
	padding: 5px; /*top/ bottom,left/right  */
	margin: 5px;  /*top, right, bottom ,left  */
	cursor: pointer;
}
#paging{
	color:black;
	padding: 5px;
	margin: 5px;
	cursor: pointer;
	/* border:1px solid black;*/
}
</style> 
</head>
<body>
<table border="1" cellpadding="5" cellspacing="0" frame="hsides" rules="rows">
	<tr>
		<th width="100">글번호</th>
		<th width="300">제목</th>
		<th width="150">작성자</th>
		<th width="100">조회수</th>
		<th width="150">작성일</th>
	</tr>


		<c:forEach var="boardDTO" items="${list}">
		<tr>
			<td align="center">${boardDTO.seq}</td>
			<td><a class="subjectA" href="#" onclick="isLogin(${boardDTO.id}, ${boardDTO.seq}, ${pg})">${boardDTO.subject}</a></td>
											
			<td align="center">${boardDTO.id}</td>
			<td align="center">${boardDTO.hit}</td>
			<td align="center">${boardDTO.logtime}</td>
		</tr>
		</c:forEach>
	
	
	
		
</table>
<div style=" margin-top: 15px; width:800px; text-align : center;'">
	${boardPaging.getPagingHTML()} 
</div>

<script type="text/javascript">	
function boardPaging(pg) {
	location.href = "/mvcBoard/board/boardList.do?pg="+ pg;
}

function isLogin(memId, seq, pg)
		{
	//alert(memId + "," + seq) //글번호도 같이 가져옴 //list에서  onclick id랑 seq같이 넎어줌
	if(memId == 'null') 
		alert("먼저 로그인하세요"); //자바 스크립트는 값이 없으면 그냥 빈값임 자바랑 다름, 
	else
		location.href="/mvcBoard/board/boardView.do?seq=" + seq + "&pg=" + pg;

}
</script>
</body>
</html>