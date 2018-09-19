package com.acl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.userBean.userBean;
import com.acl.userDao.userDao;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userDao ud = new userDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userBean ub = new userBean(username, password);
		String validity = ud.validateUser(ub);
		
		if(validity!=null) {
			ub.setFunc(ud.setmap(validity));
			request.setAttribute("bean", ub);
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}
		else {
			response.getWriter().println("<script>alert('Invalid username or password !');</script>");
		}
		
	}

}
