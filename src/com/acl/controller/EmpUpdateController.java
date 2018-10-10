package com.acl.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.bean.UserBean;
import com.acl.dao.EmployeesDao;
import com.acl.util.logfile.LogFile;

/**
 * Servlet implementation class empMng
 */
@WebServlet("/secure/employee_update")
public class EmpUpdateController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String userid = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.userid = request.getParameter("userid");
		UserBean ub;
		try {
			ub = new EmployeesDao().getDetails(userid);
			request.setAttribute("ub", ub);
			request.setAttribute("user_id", userid);
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_update.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", request.getSession().getAttribute("uname").toString(), request);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeesDao ed = new EmployeesDao();
		String username = request.getParameter("username");
		String role_id;
		try {
			role_id = ed.getRoleID(request.getParameter("user_role"));
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			boolean result = ed.updateEmp(userid, username, role_id, email, phone);
			if (result) {
				request.setAttribute("result", true);
				request.getRequestDispatcher("../WEB-INF/view/employee/employee_update.jsp").forward(request, response);
			} else {
				request.setAttribute("result", false);
				request.getRequestDispatcher("../WEB-INF/view/employee/employee_update.jsp").forward(request, response);
			}
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
		}
	}

}
