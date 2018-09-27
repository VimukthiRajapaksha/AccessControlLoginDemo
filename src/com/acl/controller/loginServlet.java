package com.acl.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acl.userBean.userBean;
import com.acl.userDao.userDao;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userDao ud = new userDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		userBean ub = new userBean(username, ud.md5(password));
		//userBean ub = new userBean(username, password);
		String roleId = ud.validateUser(ub);
		
		if(roleId!=null) {
			ub.setPages(ud.getPages(roleId));
			ub.setUsername(username);
			ub.setRole_id(roleId);
			HttpSession session = request.getSession();
			session.setAttribute("bean", ub);
			session.setAttribute("uname", username);
			session.setAttribute("roleId", roleId);
			request.getRequestDispatcher("home.jsp").forward(request, response);
			/*request.setAttribute("bean", ub);
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);*/
		}
		else {
			response.getWriter().println("<script>alert('Invalid username or password !');</script>");
			response.sendRedirect("index.html");
			
		}
		
	}

}
