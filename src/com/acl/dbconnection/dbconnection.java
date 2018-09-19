package com.acl.dbconnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class dbconnection {
	public Connection getConnection() {
		// TODO Auto-generated constructor stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
