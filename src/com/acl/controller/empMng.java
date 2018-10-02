package com.acl.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acl.logger.logger;
import com.acl.userBean.userBean;
import com.acl.userDao.employeesDao;

/**
 * Servlet implementation class empMng
 */
@WebServlet("/Manage Employees_update")
public class empMng extends HttpServlet {
	String userid = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.userid = request.getParameter("userid");
		userBean ub;
		try {
			ub = new employeesDao().getDetails(userid);
			request.setAttribute("ub", ub);
			request.setAttribute("user_id", userid);
			request.removeAttribute("uname");
			request.getRequestDispatcher("Manage Employees-update.jsp").forward(request, response);
		} catch (SQLException e) {
			new logger().getLogger(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			employeesDao ed = new employeesDao();
			String username = request.getParameter("username");
			String role_id;
			role_id = ed.getRoleID(request.getParameter("user_role"));
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			boolean result = ed.updateEmp(userid, username, role_id, email, phone);
			if (result) {
				request.setAttribute("result", true);
				request.getRequestDispatcher("Manage Employees-update.jsp").forward(request, response);
			} else {
				request.setAttribute("result", false);
				request.getRequestDispatcher("Manage Employees-update.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			new logger().getLogger(e.getMessage());
		}
	}

}
