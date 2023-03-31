package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardReplyService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//데이터
		int pseq = Integer.parseInt(request.getParameter("seq")); //pseq가 원글 번호
		String pg = request.getParameter("pg");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		
		//세션 통해서 가져옴 모든 jsp파일은 session이 내장되어 있음 write랑 비슷하게 움직이고 감
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		String email = (String) session.getAttribute("memEmail");
		
		
		Map<String, String> map = new HashMap<String, String>();  //Map<String, Object> map으로 바꾸고 
		map.put("id", id);		
		map.put("name", name);		
		map.put("email", email);		
		map.put("subject", subject);		
		map.put("content", content);
		map.put("pseq", pseq+""); //원글번호를 보내줘야 함 pseq는 int기 때문에 +"" 를 해준거임 
		
		
		
		//DB
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardReply(map);
		
		
		//응답
		return "/board/boardReply.jsp";
		
	
	}

}
