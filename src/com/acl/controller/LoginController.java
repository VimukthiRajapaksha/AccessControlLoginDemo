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

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		UserDao ud = new UserDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserBean ub;
		
		try {
			ub = new UserBean(username, ud.md5(password));
			String roleId = ud.validateUser(ub);
			if(roleId!=null) {
				ub.setPages(ud.getPages(roleId));
				ub.setUsername(username);
				ub.setRole_id(roleId);
				HttpSession session = request.getSession();
				session.setAttribute("bean", ub);
				session.setAttribute("uname", username);
				session.setAttribute("roleId", roleId);
				session.setAttribute("roles", new EmployeesDao().getRoles());
				new LogFile().getLogger("LOGGED IN AS : ", "info", username, request);
				request.getRequestDispatcher("WEB-INF/view/home.jsp").forward(request, response);
			}else {
				request.setAttribute("valid_user", false);
				request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException | SQLException | ServletException | IOException | ArithmeticException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
		}
	}
}
