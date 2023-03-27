package member.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		
		//데이터 
		String id = request.getParameter("id");
	
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();

		boolean existId = false;
		existId = memberDAO.isExistId(id);		

		
		//응답
		if (existId){
			return "/member/checkIdFail.jsp";
		}else {
			return "/member/checkIdOk.jsp"; 
		}



	}

}
