package com.acl.dbconnection;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
	
import javax.sql.DataSource;


public class dbconnection {
	public Connection getConnection() throws NamingException, SQLException {
		// TODO Auto-generated constructor stub
		/*Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;*/
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/demoDB");
		Connection conn = ds.getConnection();
		return conn;
	}
}
