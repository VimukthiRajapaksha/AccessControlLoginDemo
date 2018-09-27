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

import com.acl.userBean.userBean;
import com.acl.userDao.employeesDao;

/**
 * Servlet implementation class empMng
 */
@WebServlet("/Manage Employees_update")
public class empMng extends HttpServlet {
	//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		userBean ub = new employeesDao().getDetails(userid);
		request.setAttribute("roles", new employeesDao().getRoles());
		request.setAttribute("ub", ub);
		request.getRequestDispatcher("Manage Employees-update.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*employeesDao ed = new employeesDao();
		if(ed.getUserRole(request.getParameter("roleName").toString())==null) {
			response.getWriter().println("<script>alert('Invalid user role !'); window.history.back();</script>");
		}
		else {
			boolean result = new employeesDao().updateEmp(request.getParameter("userid").toString(), request.getParameter("userName").toString(), ed.getUserRole(request.getParameter("roleName").toString()));
			if(result) {
				response.getWriter().println("<script>alert('user updated successfully !'); window.history.back();</script>");
			}
			else {
				response.getWriter().println("<script>alert('Sorry something went wrong, try again !'); window.history.back();</script>");
			}
		}*/
		
		//System.out.println(request.getParameter("userid"));
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("user_role"));
		
		
		//System.out.println(ed.getUserRole(request.getParameter("roleName")));
		}
		
		
		
		
		
}
