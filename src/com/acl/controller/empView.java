package com.acl.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;
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
 * Servlet implementation class empView
 */
@WebServlet("/secured/employee_view")
public class empView extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		employeesDao ed = new employeesDao();
		userDao ud = new userDao();
		try {
			ArrayList<userBean> view = ed.getView();
			HttpSession session = request.getSession();
			userBean b = (userBean) session.getAttribute("bean");
			b.setFunctions(ud.getFunctions(b.getRole_id(), request.getParameter("page").toString()));
			session.setAttribute("emp_bean", view);
			session.setAttribute("bean", b);
			session.setAttribute("page", request.getParameter("page").toString());
			request.getRequestDispatcher("empView.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new logger().getLogger(e.getMessage(), "warn", request.getSession().getAttribute("uname").toString(), request);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		employeesDao ed = new employeesDao();
		userDao ud = new userDao();
		System.out.println(request.getAttribute("Delresult")+"doget empView");
		try {
			ArrayList<userBean> view = ed.getSearchView(request.getParameter("keyWord").toString());
			HttpSession session = request.getSession();
			userBean b = (userBean) session.getAttribute("bean");
			b.setFunctions(ud.getFunctions(b.getRole_id(), request.getParameter("page").toString()));
			session.setAttribute("emp_bean", view);
			session.setAttribute("bean", b);
			//request.setAttribute("Delresult", request.getAttribute("Delresult"));
			session.setAttribute("page", request.getParameter("page").toString());
			request.getRequestDispatcher("empView.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new logger().getLogger(e.getMessage(), "warn", request.getSession().getAttribute("uname").toString(), request);
		}
	}
}
