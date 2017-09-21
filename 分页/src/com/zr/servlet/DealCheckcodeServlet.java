package com.zr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DealCheckcodeServlet
 */
@WebServlet("/DealCheckcodeServlet")
public class DealCheckcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DealCheckcodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
		String jugdeCode = (String)request.getSession().getAttribute("judgeCode");
		System.out.println(jugdeCode);
		if(code.equalsIgnoreCase(jugdeCode)){
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else{
			String errorMsg = "—È÷§¬Î”–¥ÌŒÛ";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("judgeCode.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
