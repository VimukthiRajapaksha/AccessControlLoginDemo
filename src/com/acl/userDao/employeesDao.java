package com.acl.userDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public boolean updateEmp(String user_id, String user_name, String role_id){
		int result=0;
		Connection con = new dbconnection().getConnection();
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate("UPDATE user SET role_id = '"+role_id+"', username = '"+user_name+"' WHERE user_id = '"+user_id+"'");
			if(result!=0) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String getUserRole(String role_name){
		String result=null;
		Connection con = new dbconnection().getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select role_id from role WHERE role_name='"+role_name+"'");
			if(rs.next()) {
				result = rs.getString(1);
				return result;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean deleteEmp(String user_id){
		int result=0;
		Connection con = new dbconnection().getConnection();
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate("DELETE FROM user WHERE user_id='"+user_id+"'");
			if(result!=0) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
