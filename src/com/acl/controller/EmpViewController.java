package com.acl.controller;

import java.io.IOException;
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
 * Servlet implementation class empView
 */
@WebServlet("/secure/employee")
public class EmpViewController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmployeesDao ed = new EmployeesDao();
		UserDao ud = new UserDao();
		
		try {
			ArrayList<UserBean> view = ed.getView();
			HttpSession session = request.getSession();
			UserBean b = (UserBean) session.getAttribute("bean");
			b.setFunctions(ud.getFunctions(b.getRole_id(), request.getParameter("page").toString()));
			session.setAttribute("emp_bean", view);
			session.setAttribute("bean", b);
			session.setAttribute("page", request.getParameter("page").toString());
			request.getRequestDispatcher("..//WEB-INF/view/employee/employee_view.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", request.getSession().getAttribute("uname").toString(), request);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmployeesDao ed = new EmployeesDao();
		UserDao ud = new UserDao();
		
		try {
			ArrayList<UserBean> view = ed.getSearchView(request.getParameter("keyWord").toString());
			HttpSession session = request.getSession();
			UserBean b = (UserBean) session.getAttribute("bean");
			b.setFunctions(ud.getFunctions(b.getRole_id(), request.getParameter("page").toString()));
			session.setAttribute("emp_bean", view);
			session.setAttribute("bean", b);
			session.setAttribute("page", request.getParameter("page").toString());
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_view.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", request.getSession().getAttribute("uname").toString(), request);
		}
	}
}
