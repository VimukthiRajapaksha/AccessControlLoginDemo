package com.acl.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class empView
 */
@WebServlet("/secure/employee")
public class EmpViewController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = null;
		try {
			HttpSession session = request.getSession();
			EmployeesDao ed = new EmployeesDao();
			UserDao ud = new UserDao();
			UserBean b = new UserBean();

			username = session.getAttribute("uname").toString();
			String roleId = session.getAttribute("roleId").toString();
			String page = request.getParameter("page");

			ArrayList<UserBean> view = ed.getView();
			b.setPages(ud.getPages(roleId));
			b.setFunctions(ud.getFunctions(roleId, page));
			double count = Double.parseDouble(ed.getEmpCount());
			int pageCount = (int) (((count / 7) % 1 == 0) ? count / 7 : (count / 7) + 1);
			session.setAttribute("page", page);

			request.setAttribute("count", count);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("emp_bean", view);
			request.setAttribute("bean", b);
			request.setAttribute("limit", 7);

			request.getRequestDispatcher("..//WEB-INF/view/employee/employee_view.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmployeesDao ed = new EmployeesDao();
		UserDao ud = new UserDao();
		ArrayList<UserBean> view = null;
		UserBean b = new UserBean();

		String limit = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String pageNo = request.getParameter("pageNo");
		String keyWord = request.getParameter("keyWord");
		String username = session.getAttribute("uname").toString();
		String roleId = session.getAttribute("roleId").toString();
		String page = session.getAttribute("page").toString();
		if (pageNo == null || Integer.parseInt(pageNo) <= 1) {
			pageNo = "1";
		}
		if (offset == null || Integer.parseInt(offset) <= 1) {
			offset = "0";
		}
		if (limit == null || Integer.parseInt(limit) <= 1) {
			limit = "7";
		}
		try {
			double count = Double.parseDouble(ed.getEmpCount());
			int pageCount = (int) (((count / Integer.parseInt(limit)) % 1 == 0) ? count / Integer.parseInt(limit)
					: (count / Integer.parseInt(limit)) + 1);

			pageNo = (Integer.parseInt(pageNo) >= pageCount) ? pageCount + "" : pageNo;
			view = ed.getView(Integer.parseInt(limit), Integer.parseInt(offset));

			if (keyWord != null) {
				view = ed.getSearchView(keyWord);
			}

			b.setFunctions(ud.getFunctions(roleId, page));
			b.setPages(ud.getPages(roleId));

			request.setAttribute("limit", limit);
			request.setAttribute("count", ed.getEmpCount());
			request.setAttribute("emp_bean", view);
			request.setAttribute("bean", b);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("page", page);
			request.getRequestDispatcher("../WEB-INF/view/employee/employee_view.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			new LogFile().getLogger(e.getMessage(), "warn", username, request);
			String error = e.getMessage() + "(" + e.getClass().toString() + ")";
			request.setAttribute("error", error);
			request.getRequestDispatcher("../error.jsp").forward(request, response);
		}
	}
}
