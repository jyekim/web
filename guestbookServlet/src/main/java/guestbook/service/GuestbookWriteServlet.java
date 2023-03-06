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

@WebServlet("/GuestbookWriteServlet")
public class GuestbookWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//데이터
		request.setCharacterEncoding("UTF-8");//post인 경우에 쓰는것 안에서 내부적으로 페이지 넘겨주는것이기에 request response 다 각각 한글처리해야함
		
		String guest = request.getParameter("guest");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String title = request.getParameter("title");
		String content= request.getParameter("content");
		
		
		GuestbookDTO guestbookDTO = new GuestbookDTO();
		guestbookDTO.setGuest(guest);
		guestbookDTO.setEmail(email);
		guestbookDTO.setHomepage(homepage);
		guestbookDTO.setTitle(title);
		guestbookDTO.setContent(content);
		
		
		//DB연동
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();// 이게 dao에 있는 새로 생성된 것을 그대로 가져오는 것
		guestbookDAO.guestbookWrite(guestbookDTO);
	

		//응답 
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h3> 작성하신 글을 작성하였습니다.</h3>");
		out.println("<button type='button' onclick=\"location.href='/guestbookServlet/GuestbookListServlet'\">글목록</button>");
		out.println("</body>");
		out.println("</html>");
	}

}
