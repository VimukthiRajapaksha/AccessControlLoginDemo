package com.acl.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acl.userBean.userBean;
import com.acl.userDao.employeesDao;

/**
 * Servlet implementation class empDel
 */
@WebServlet("/Manage Employees_delete")
public class empDel extends HttpServlet {
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { employeesDao ed = new
	 * employeesDao(); ResultSet rs = ed.getView();
	 * 
	 * ArrayList<userBean> view = new ArrayList<userBean>();
	 * 
	 * try { while(rs.next()) { userBean bean = new userBean();
	 * bean.setUserid(rs.getString(1)); bean.setUsername(rs.getString(2));
	 * bean.setRole_name(rs.getString(3)); view.add(bean); } } catch (SQLException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } HttpSession
	 * session = request.getSession(); session.setAttribute("emp_bean", view);
	 * request.getRequestDispatcher("Manage Employees-delete.jsp").forward(request,
	 * response); }
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		employeesDao ed = new employeesDao();
		boolean result = new employeesDao().deleteEmp(request.getParameter("userid").toString());
		if (result) {
			response.getWriter()
					.println("<script>alert('user deleted successfully !'); window.history.back(); location.reload();</script>");
		} else {
			response.getWriter().println(
					"<script>alert('Sorry something went wrong, try again !'); window.history.back(); location.reload();</script>");
		}
		System.out.println(request.getParameter("userid"));
	}

}