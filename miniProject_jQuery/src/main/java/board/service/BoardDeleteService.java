package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardDeleteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//데이터
		String seq = request.getParameter("seq");
		
		
		// DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardDelete(seq);
		
		// 응답 
		//request.setAttribute("display", "/board/boardList.jsp");   // index로 바로 보내면 pg값을 못 받아서 에러가 떨어짐
		return "/board/boardDelete.jsp";
	}

}
