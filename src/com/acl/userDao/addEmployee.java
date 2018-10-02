package com.acl.userDao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.dbconnection.dbconnection;
import com.acl.logger.logger;

/**
 * Servlet implementation class addEmployee
 */
@WebServlet("/addEmployee")
public class addEmployee extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		boolean result = false;
		try {
			result = new userDao().addUser(type, username, password, email, phone);
		} catch (NoSuchAlgorithmException | SQLException e) {
			new logger().getLogger(e.getMessage(), "warn", username, request);
		}
		if (result) {
			request.setAttribute("result", true);
			request.getRequestDispatcher("Manage Employees_create.jsp").forward(request, response);
		} else {
			request.setAttribute("result", false);
			request.getRequestDispatcher("Manage Employees_create.jsp").forward(request, response);
		}
	}

}
