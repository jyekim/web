package board.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import board.dao.BoardDAO;

public class BoardWriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		//데이터 
		
		String subject = request.getParameter("subject"); //bwform에서 요청한 것을 받아오는 것 
		String content = request.getParameter("content");
		
		//세션 통해서 가져옴 모든 jsp파일은 session이 내장되어 있음
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		String email = (String) session.getAttribute("memEmail"); //이 이메일 세션은 없으니 만들러 가야함 어디로? login으로 
		
		//제대로 세션이 들어오고 있는지 하나씩 찍어봐서 확인할 수 있음 
//		System.out.println(id);
//		System.out.println(name);
//		System.out.println(email);
//		System.out.println(subject);
//		System.out.println(content);
		
				
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("id", id);		
		map.put("name", name);		
		map.put("email", email);		
		map.put("subject", subject);		
		map.put("content", content);	
		
		
		//DB 
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.boardWrite(map);//map에서 꺼내달라는 것으로 해석하면 됨 즉, id나 name,email등 이름으로 관리한다는게 이런 뜻 
		
		return "/board/boardWrite.jsp";
	}

}
