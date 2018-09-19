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
 * Servlet implementation class empView
 */
@WebServlet("/empView")
public class empView extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		employeesDao ed = new employeesDao();
		ResultSet rs = ed.getView();
		request.setAttribute("rs", rs);
		RequestDispatcher rd = request.getRequestDispatcher("empView.jsp");
		rd.forward(request, response);
	}
}
