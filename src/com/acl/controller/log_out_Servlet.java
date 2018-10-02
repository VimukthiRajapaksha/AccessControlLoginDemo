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
@WebServlet("/logout")
public class log_out_Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout post called");
		request.getSession().removeAttribute("bean");
		request.getSession().removeAttribute("uname");
		request.getSession().removeAttribute("roleId");
		request.getSession().removeAttribute("page");
		request.getSession().removeAttribute("ub");
		request.getSession().removeAttribute("roles");
		request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
