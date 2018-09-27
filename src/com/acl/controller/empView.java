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
import com.acl.userDao.userDao;

/**
 * Servlet implementation class empView
 */
@WebServlet("/empView")
public class empView extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		employeesDao ed = new employeesDao();
		userDao ud = new userDao();
		ResultSet rs = ed.getView();
		ArrayList<userBean> view = new ArrayList<userBean>();
		
		try {
			while(rs.next()) {
				userBean bean = new userBean();
				bean.setUserid(rs.getString(1));
				bean.setUsername(rs.getString(2));
				bean.setRole_name(rs.getString(3));
				view.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		userBean b = (userBean) session.getAttribute("bean");
		b.setFunctions(ud.getFunctions(b.getRole_id(), request.getParameter("page").toString()));
		session.setAttribute("emp_bean", view);
		session.setAttribute("bean", b);
		session.setAttribute("page", request.getParameter("page").toString());
		request.getRequestDispatcher("empView.jsp").forward(request, response);
	}
}
