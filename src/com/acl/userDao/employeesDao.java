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
			rs = st.executeQuery("select u.user_id, u.username, r.role_name from user u, role r where u.role_id=r.role_id");
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
			rs1 = st1.executeQuery("select f.fun_name, f.fun_url, p.page_name from function f, page p, page_functions pf, role r, user u where f.fun_id=pf.fun_id and p.page_id=pf.page_id and r.role_id = pf.role_id and u.role_id=r.role_id and p.page_url='"+page+"' and u.username='"+username+"'");
			return rs1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs1;
	}
}
