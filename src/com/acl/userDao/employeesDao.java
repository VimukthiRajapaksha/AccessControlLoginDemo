package com.acl.userDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.acl.dbconnection.dbconnection;

public class employeesDao {
	Connection con = new dbconnection().getConnection();
	public ResultSet getView() {
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery("select * from user");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getPermission(String username, String page) {
		ResultSet rs1 = null;
		try {
			Statement st1 = con.createStatement();
			rs1 = st1.executeQuery("select up.c, up.u, up.d from user_pages up, page p, user u where u.user_id=p.page_id and u.user_id=up.user_id and p.page_id=up.page_id and u.username='"+username+"' and p.page_url='"+page+"'");
			return rs1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs1;
	}
}
