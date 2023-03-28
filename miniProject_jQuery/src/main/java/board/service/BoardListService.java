package board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import board.bean.BoardDTO;
import board.bean.BoardPaging;
import board.dao.BoardDAO;

public class BoardListService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		//데이터 
		int pg = Integer.parseInt(request.getParameter("pg"));
		
			request.setAttribute("pg", pg);
			request.setAttribute("display", "/board/boardList.jsp");
			return "/index.jsp";
	} //index가 뜨고 그 안에 목록이 들어오는 것 

}
