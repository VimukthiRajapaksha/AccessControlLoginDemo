package com.acl.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acl.bean.UserBean;
import com.acl.dao.EmployeesDao;
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
		HttpSession session = request.getSession();
		EmployeesDao ed = new EmployeesDao();
		UserDao ud = new UserDao();
		UserBean b = new UserBean();
		
		String username = session.getAttribute("uname").toString();
		String roleId = session.getAttribute("roleId").toString();
		String page = session.getAttribute("page").toString();
		ArrayList<UserBean> view = null;
		try {
			view = ed.getView();
			b.setPages(ud.getPages(roleId));
			b.setFunctions(ud.getFunctions(roleId, page));
			request.setAttribute("roles", ed.getRoles());
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
		
		request.setAttribute("emp_bean", view);
		request.setAttribute("bean", b);
		request.getRequestDispatcher("../WEB-INF/view/employee/employee_create.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		EmployeesDao ed = new EmployeesDao();
		UserDao ud = new UserDao();
		UserBean b = new UserBean();
		
		String uname = session.getAttribute("uname").toString();
		String roleId = session.getAttribute("roleId").toString();
		String page = session.getAttribute("page").toString();
		ArrayList<UserBean> view = null;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		boolean result = false;

		try {
			view = ed.getView();
			b.setPages(ud.getPages(roleId));
			b.setFunctions(ud.getFunctions(roleId, page));
			request.setAttribute("roles", ed.getRoles());
			result = new UserDao().addUser(type, username, password, email, phone);
		} catch (NoSuchAlgorithmException | SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
		if (result) {
			request.setAttribute("emp_bean", view);
			request.setAttribute("bean", b);
			request.setAttribute("result", true);
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_create.jsp").forward(request, response);
		} else {
			request.setAttribute("emp_bean", view);
			request.setAttribute("bean", b);
			request.setAttribute("result", false);
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_create.jsp").forward(request, response);
		}
	}

}
