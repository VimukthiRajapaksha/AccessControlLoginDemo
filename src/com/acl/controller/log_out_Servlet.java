package com.acl.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class log_out_Servlet
 */
@WebServlet("/acl/log_out_")
public class log_out_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public log_out_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout called");
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.removeAttribute("uname");
			session.invalidate();
		}
		request.getRequestDispatcher("index.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout called");
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.removeAttribute("uname");
			session.invalidate();
		}
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}
