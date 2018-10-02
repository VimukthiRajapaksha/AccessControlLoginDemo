package com.acl.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acl.logger.logger;
import com.acl.userBean.userBean;
import com.acl.userDao.employeesDao;
import com.acl.userDao.userDao;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
		userDao ud = new userDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		userBean ub;
		try {
			ub = new userBean(username, ud.md5(password));
			String roleId = ud.validateUser(ub);
			
			if(roleId!=null) {
				ub.setPages(ud.getPages(roleId));
				ub.setUsername(username);
				ub.setRole_id(roleId);
				HttpSession session = request.getSession();
				session.setAttribute("bean", ub);
				session.setAttribute("uname", username);
				session.setAttribute("roleId", roleId);
				session.setAttribute("roles", new employeesDao().getRoles());
				new logger().getLogger("LOGGED IN AS : ", "info", username, request);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			else {
				request.setAttribute("valid_user", false);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException | SQLException | ServletException | IOException | ArithmeticException e) {
			new logger().getLogger(e.getMessage(), "warn", username, request);
		}
	}
}
