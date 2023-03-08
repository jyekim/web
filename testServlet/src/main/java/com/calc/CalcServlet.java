package com.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1.데이터 받기 
	 int x = Integer.parseInt(request.getParameter("x"));
	 int y = Integer.parseInt(request.getParameter("y"));
		
		
	//2.응답
	 response.setContentType("text/html;charset=UTF-8");
	 PrintWriter out = response.getWriter();
	 out.println("<html>");
	 out.println("<body>");
	 out.println("<h3>");
	 
	 out.println(x + "+" + y +"=" + (x+y)+"<br>");
	 out.println(x + "-" + y +"=" + (x-y)+"<br>");
	 out.println(x + "*" + y +"=" + (x*y)+"<br>");
	 out.println(x + "/" + y +"=" + ((double)x/y)+"<br>");
	 out.println("<input type='button' value='뒤로' onclick='javascript:history.go(-1)'>");	//단순히 파일만 뒤로가기 하는거 
	 out.println("<input type='button' value='뒤로' onclick=location.href='http://localhost:8080/testServlet/calc/input.html'>");	 
	 out.println("<input type='button' value='연령제한' onclick=location.href='http://localhost:8080/testServlet/param.html'>");	 
	 out.println("</h3>");
	 out.println("</body>");
	 out.println("</html>");
	
		
	}
	


}
