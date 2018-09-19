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
		String type = request.getParameter("type");
		int result = 0;
		Connection con = new dbconnection().getConnection();
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate("insert into user values(null, '"+type+"' ,'"+username+"', '"+password+"')");
			if(result!=0) {
				response.getWriter().println("<script>alert('user added successfully !');</script>");
			}
			else {
				response.getWriter().println("<script>alert('Sorry something went wrong, try again !');</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
