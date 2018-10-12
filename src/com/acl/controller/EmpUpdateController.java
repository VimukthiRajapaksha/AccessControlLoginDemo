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
		HttpSession session = request.getSession();
		EmployeesDao ed = new EmployeesDao();
		UserDao ud = new UserDao();
		UserBean b = new UserBean();
		
		String username = session.getAttribute("uname").toString();
		String roleId = session.getAttribute("roleId").toString();
		String page = session.getAttribute("page").toString();
		
		UserBean ub;
		try {
			ArrayList<UserBean> view = ed.getView();
			b.setPages(ud.getPages(roleId));
			b.setFunctions(ud.getFunctions(roleId, page));
			ub = new EmployeesDao().getDetails(userid);
			request.setAttribute("emp_bean", view);
			request.setAttribute("bean", b);
			request.setAttribute("ub", ub);
			request.setAttribute("roles", ed.getRoles());
			request.setAttribute("user_id", userid);
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_update.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}

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
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String role_id;
		
		try {
			ArrayList<UserBean> view = ed.getView();
			b.setPages(ud.getPages(roleId));
			b.setFunctions(ud.getFunctions(roleId, page));
			role_id = ed.getRoleID(request.getParameter("user_role"));
			
			boolean result = ed.updateEmp(userid, username, role_id, email, phone);
			if (result) {
				request.setAttribute("emp_bean", view);
				request.setAttribute("bean", b);
				request.setAttribute("result", true);
				request.getRequestDispatcher("../WEB-INF/view/employee/employee_update.jsp").forward(request, response);
			} else {
				request.setAttribute("emp_bean", view);
				request.setAttribute("bean", b);
				request.setAttribute("result", false);
				request.getRequestDispatcher("../WEB-INF/view/employee/employee_update.jsp").forward(request, response);
			}
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", uname, request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
	}

}
