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
import com.acl.userDao.employeesDao;

//f.fun_name, f.fun_url, p.page_name
@WebServlet("/employees")
public class employees extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page").toString();
		HttpSession session = request.getSession();
		session.setAttribute("page", page);
		
		System.out.println(session.getAttribute("roleId").toString()+"--roleId");
		employeesDao ed = new employeesDao();
		ResultSet rs1 = ed.getPermission(request.getParameter("username").toString(), page);
		ArrayList <functionBean> func= new ArrayList<>();
		
		try {
			while(rs1.next()) {
				functionBean fb = new functionBean();
				fb.setFun_name(rs1.getString("fun_name"));
				fb.setFun_url(rs1.getString("fun_url"));
				fb.setPage_name(rs1.getString("page_name"));
				
				func.add(fb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("rs1", func);
		request.setAttribute("result", request.getAttribute("result"));
		request.getRequestDispatcher("employees.jsp").forward(request, response);
	}

}
