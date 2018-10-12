package com.acl.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.dao.EmployeesDao;
import com.acl.util.logfile.LogFile;

/**
 * Servlet implementation class empDel
 */
@WebServlet("/secure/employee_delete")
public class EmpDeleteController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result;
		try {
			result = new EmployeesDao().deleteEmp(request.getParameter("userid").toString());
			if (result) {
				request.setAttribute("page", request.getAttribute("page"));
				request.setAttribute("Delresult", true);
				request.getRequestDispatcher("employee").forward(request, response);

			} else {
				request.setAttribute("page", request.getAttribute("page"));
				request.setAttribute("Delresult", false);
				request.getRequestDispatcher("employee").forward(request, response);
			}
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", request.getSession().getAttribute("uname").toString(), request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
	}

}
