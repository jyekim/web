package guestbook.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;


@WebServlet("/GuestbookListServlet")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터 
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		//페이징 처리 - 1페이지당 2개씩
		/*
		 pg=1     rn>=1 and rn<=2 
		 pg=2     rn>=2 and rn<=4
		 pg=3     rn>=5 and rn<=6
		 */
		
		int endNum = pg*2;
		int startNum = endNum-1; 
		
		//DB
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		ArrayList<GuestbookDTO> list = guestbookDAO.guestbookList(startNum, endNum);
		System.out.println(list.size()+","+ startNum +","+endNum);
		
		
		//총글수 
		int totalA = guestbookDAO.getTotalA();
		System.out.println(totalA);

		//총 페이지수
		int totalP = (totalA + 1)/ 2;
		
		/*
		 총글수 : 7
		 총페이지수 : 4
		 
		 총글수 : 8 
		 총페이지수 : 4 
		 */
		
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); //스트림 생성 //여기서 out 객체는 웹브라우저로 나오는 보내는 객체임 그럼 브라우저는 번역을 해서 화면에 결과를 보여줌
	
		
		out.println("<html>");
		out.println("<style>");
		
		out.println("#currentPagingDiv {float:left; border: 1px red solid; width:20px; height:20px; margin-left:5px; text-align: center;}");
		out.println("#currentDiv {float:left; width:20px; height:20px; margin-left:5px; text-align: center;}");
		
		out.println("#currentPaging {color: red; text-decoration: none;}");
		out.println("#paging {color: black; text-decoration: none;}");
		
		out.println("</style>");
		out.println("<body>");
		
		
		//페이지 번호 
		for(int i=1; i<=totalP; i++) {
			if(i == pg)
				out.println("<div id='currentPagingDiv'><a href='/guestbookServlet/GuestbookListServlet?pg=" + i +"'>" + i + "</a></div>");
			else
			out.println("<div id='pageingDiv'><a href='/guestbookServlet/GuestbookListServlet?pg=" + i +"'>" + i + "</a></div>");		
		}
		out.println("<br><br>");
		
		
		if(list != null) {
			for (GuestbookDTO guestbookDTO : list) {
				out.println("<table border='1' cellpadding='5' cellspacing='0'>");
				
				out.println("<tr>");
				out.println("<td width='150'>작성자</td>");
				out.println("<td width='150'>" + guestbookDTO.getGuest() + "</td>");
				out.println("<td width='150'>작성일</td>");
				out.println("<td width='150'>" + guestbookDTO.getLogtime() + "</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>이메일</td>");
				out.println("<td colspan='3'>" + guestbookDTO.getEmail() + "</td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td>홈페이지</td>");
				out.println("<td colspan='3'>" + guestbookDTO.getHomepage() + "</td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td>제목</td>");
				out.println("<td colspan='3'>" + guestbookDTO.getTitle() + "</td>");
				out.println("</tr>");
				
				
				out.println("<tr>");
				out.println("<td colspan='4'><pre>" + guestbookDTO.getContent() + "</pre></td>");
				out.println("</tr>");
				
				
				out.println("</table>"); 
				out.println("<hr style='border-color: red;  width: 700px; margin:10px 0;'>");
			}
		}
		out.println("</body>");
		out.println("</html>");
	}

}
