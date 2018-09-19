package com.acl.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.userDao.employeesDao;

/**
 * Servlet implementation class employees
 */
@WebServlet("/employees")
public class employees extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("called ");
		employeesDao ed = new employeesDao();
		ResultSet rs1 = ed.getPermission(request.getParameter("username").toString(), request.getParameter("page").toString());
		request.setAttribute("rs1", rs1);
		RequestDispatcher rd = request.getRequestDispatcher("employees.jsp");
		rd.forward(request, response);
	}

}
