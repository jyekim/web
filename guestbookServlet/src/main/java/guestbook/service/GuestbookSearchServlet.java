package guestbook.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;


@WebServlet("/GuestbookSearchServlet")
public class GuestbookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터
		String seq = request.getParameter("seq");
		
		
		//DB
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		GuestbookDTO guestbookDTO = guestbookDAO.guestbookSearch(seq);
		
		
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); //스트림 생성 //여기서 out 객체는 웹브라우저로 나오는 보내는 객체임 그럼 브라우저는 번역을 해서 화면에 결과를 보여줌
	
		out.println("<html>");
		out.println("<body>");
		
		if(guestbookDTO != null) {
			out.println("<table border='1' cellpadding='5' cellspacing='0'>");
			
			out.println("<tr>");
			out.println("<td>작성자</td>");
			out.println("<td>" + guestbookDTO.getGuest() + "</td>");
			out.println("<td>작성일</td>");
			out.println("<td>" + guestbookDTO.getLogtime() + "</td>");
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
			out.println("<td colspan='4' height='150'><pre>" + guestbookDTO.getContent() + "</pre></td>");
			out.println("</tr>");
			
			
			out.println("</table>");
			out.println("</hr>");
			
			
		}else {
			out.println("<h3>글번호가 없습니다</h3>");
		}
		out.println("</html>");
		out.println("</body>");
		
	}

}
