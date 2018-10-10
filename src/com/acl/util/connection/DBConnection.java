package com.acl.util.connection;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
	
import javax.sql.DataSource;


public class DBConnection {
	public Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/demoDB");
		Connection conn = ds.getConnection();
		return conn;
	}
}
