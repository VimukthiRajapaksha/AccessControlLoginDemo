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

import com.acl.functionBean.functionBean;
import com.acl.logger.logger;
import com.acl.userDao.employeesDao;

//f.fun_name, f.fun_url, p.page_name
@WebServlet("/employees")
public class employees extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page").toString();
		HttpSession session = request.getSession();
		session.setAttribute("page", page);
		String username = request.getParameter("username").toString();
		System.out.println(session.getAttribute("roleId").toString()+"--roleId");
		employeesDao ed = new employeesDao();
		ArrayList<functionBean> func;
		try {
			func = ed.getPermission(username, page);
			session.setAttribute("rs1", func);
			request.setAttribute("result", request.getAttribute("result"));
			request.getRequestDispatcher("employees.jsp").forward(request, response);
		} catch (SQLException e) {
			new logger().getLogger(e.getMessage(), "warn", username, request);
		}
		
	}

}
