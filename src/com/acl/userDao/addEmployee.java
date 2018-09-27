package com.acl.userDao;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acl.dbconnection.dbconnection;

/**
 * Servlet implementation class addEmployee
 */
@WebServlet("/addEmployee")
public class addEmployee extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().println("called");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		int result = 0;
		Connection con = new dbconnection().getConnection();
		try {
			String query = "insert into user values(null, ? , ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setObject(1, type);
			ps.setObject(2, username);
			ps.setObject(3, new userDao().md5(password));
			ps.setObject(4, email);
			ps.setObject(5, phone);
			
			result = ps.executeUpdate();

			if(result!=0) {
				request.setAttribute("result", true);
				request.getRequestDispatcher("Manage Employees_create.jsp").forward(request, response);
			}
			else {
				request.setAttribute("result", false);
				request.getRequestDispatcher("Manage Employees_create.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
