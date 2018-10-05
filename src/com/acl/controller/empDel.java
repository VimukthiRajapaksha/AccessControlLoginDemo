package com.acl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.logger.logger;
import com.acl.userDao.employeesDao;

/**
 * Servlet implementation class empDel
 */
@WebServlet("/secured/Manage Employees_delete")
public class empDel extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result;
		System.out.println("empDel servlet called");
		try {
			result = new employeesDao().deleteEmp(request.getParameter("userid").toString());
			if (result) {
				request.setAttribute("page", request.getAttribute("page"));
				request.setAttribute("Delresult", true);
				request.getRequestDispatcher("employee_view").forward(request, response);

			} else {
				request.setAttribute("page", request.getAttribute("page"));
				request.setAttribute("Delresult", false);
				request.getRequestDispatcher("employee_view").forward(request, response);
			}
		} catch (SQLException | NamingException e) {
			request.setAttribute("page", request.getAttribute("page"));
			request.setAttribute("Delresult", false);
			request.getRequestDispatcher("employee_view").forward(request, response);
			new logger().getLogger(e.getMessage(), "warn", request.getSession().getAttribute("uname").toString(), request);
		}
	}

}
