package com.acl.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.dao.UserDao;
import com.acl.util.logfile.LogFile;

/**
 * Servlet implementation class addEmployee
 */
@WebServlet("/secure/employee_add")
public class EmpAddController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("../WEB-INF/view/employee/employee_create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		boolean result = false;

		try {
			result = new UserDao().addUser(type, username, password, email, phone);
		} catch (NoSuchAlgorithmException | SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
		}
		if (result) {
			request.setAttribute("result", true);
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_create.jsp").forward(request, response);
		} else {
			request.setAttribute("result", false);
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_create.jsp").forward(request, response);
		}
	}

}
