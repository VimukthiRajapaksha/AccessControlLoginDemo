package com.acl.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.userDao.employeesDao;

/**
 * Servlet implementation class empDel
 */
@WebServlet("/Manage Employees_delete")
public class empDel extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result = new employeesDao().deleteEmp(request.getParameter("userid").toString());
		if (result) {
			// response.getWriter().println("<script>alert('user deleted successfully
			// !');</script>");
			request.setAttribute("page", request.getAttribute("page"));
			request.setAttribute("result", true);
			request.getRequestDispatcher("emp").forward(request, response);

		} else {
			request.setAttribute("page", request.getAttribute("page"));
			request.setAttribute("result", false);
			request.getRequestDispatcher("emp").forward(request, response);
			/*response.getWriter().println(
					"<script>window.history.back(); location.reload();alert('Sorry something went wrong, try again !');</script>");*/
		}
		// System.out.println(request.getParameter("userid"));
	}

}
