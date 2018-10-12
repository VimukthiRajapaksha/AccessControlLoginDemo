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
import javax.servlet.http.HttpSession;

import com.acl.bean.UserBean;
import com.acl.dao.EmployeesDao;
import com.acl.dao.UserDao;
import com.acl.util.logfile.LogFile;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/home")
public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			redirectToHome(request, response);
		} catch (NoSuchAlgorithmException | SQLException | NamingException | ServletException | IOException e) {
			new LogFile().getLogger(e.getMessage(), "warn", "no user name", request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			redirectToHome(request, response);
		} catch (NoSuchAlgorithmException | SQLException | NamingException | ServletException | IOException e) {
			new LogFile().getLogger(e.getMessage(), "warn", "no user name", request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
	}

	private void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, SQLException, NamingException, ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleId = (String) session.getAttribute("roleId");
		String uname = (String) session.getAttribute("uname");
		UserDao ud = new UserDao();
		UserBean ub = null;
		
		if(username!=null && password!=null) {
			ub = new UserBean(username, ud.md5(password));
			roleId = ud.validateUser(ub);
			if(roleId!=null) {
				ub.setPages(ud.getPages(roleId));
				ub.setUsername(username);
				ub.setRole_id(roleId);
				session.setAttribute("uname", username);
				session.setAttribute("roleId", roleId);
				request.setAttribute("bean", ub);
				request.setAttribute("roles", new EmployeesDao().getRoles());
				new LogFile().getLogger("LOGGED IN AS : ", "info", username, request);
				request.getRequestDispatcher("WEB-INF/view/home.jsp").forward(request, response);
			}else {
				request.setAttribute("valid_user", false);
				request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
			}
		}else if(uname!=null && roleId!=null) {
			ub = new UserBean();
			ub.setPages(ud.getPages(roleId));
			ub.setUsername(username);
			ub.setRole_id(roleId);
			request.setAttribute("bean", ub);
			request.setAttribute("roles", new EmployeesDao().getRoles());
			request.getRequestDispatcher("WEB-INF/view/home.jsp").forward(request, response);
		}
	}
}
