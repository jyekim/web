package board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class BoardWriteFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("display", "/board/boardWriteForm.jsp");  //메인화면에서 writeform만 나오는것
		return "/index.jsp";
	}

}
