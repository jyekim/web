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
		 
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		MemberDTO memberDTO = memberDAO.memberLogin(map); 
		
		
		//응답
		if (memberDTO == null) {
			return "/member/checkIdOk.jsp"; 
		}else {
			return "/member/checkIdFail.jsp";
		}



	}

}
