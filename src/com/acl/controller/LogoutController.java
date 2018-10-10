package com.acl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class log_out_Servlet
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int timeout = Integer.parseInt(request.getParameter("timeout"));
		timeout=1;
		System.out.println("doget logout called !");
		System.out.println(request.getContextPath()+"/index.jsp");
		//request.getSession().invalidate();
		if (timeout == 1)
			request.setAttribute("timeoutErr", "Session has time out !");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
