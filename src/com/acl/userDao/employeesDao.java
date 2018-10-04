package com.acl.userDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;

import com.acl.dbconnection.dbconnection;
import com.acl.functionBean.functionBean;
import com.acl.userBean.userBean;
import com.mysql.fabric.xmlrpc.base.Array;

public class employeesDao {

	public ArrayList<userBean> getView() throws SQLException, NamingException {
		Connection con = new dbconnection().getConnection();
		ResultSet rs = null;
		ArrayList<userBean> results = new ArrayList<userBean>();

		String query = "select u.user_id, u.username, r.role_name, u.email, u.phone from user u, role r where u.role_id=r.role_id order by u.user_id";
		PreparedStatement ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			userBean ub = new userBean();
			ub.setUserid(rs.getString(1));
			ub.setUsername(rs.getString(2));
			ub.setRole_name(rs.getString(3));
			ub.setEmail(rs.getString(4));
			ub.setPhone(rs.getString(5));

			results.add(ub);
		}
		con.close();
		return results;
	}

	public ArrayList<userBean> getSearchView(String key) throws SQLException, NamingException {
		Connection con = new dbconnection().getConnection();
		ResultSet rs = null;
		ArrayList<userBean> results = new ArrayList<userBean>();

		String query = "select u.user_id, u.username, r.role_name, u.email, u.phone from user u, role r where (u.user_id like ? or u.username like ? or r.role_name like ? or u.email like ? or u.phone like ?) and (u.role_id=r.role_id)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setObject(1, "%" + key + "%");
		ps.setObject(2, "%" + key + "%");
		ps.setObject(3, "%" + key + "%");
		ps.setObject(4, "%" + key + "%");
		ps.setObject(5, "%" + key + "%");
		rs = ps.executeQuery();
		while (rs.next()) {
			userBean ub = new userBean();
			ub.setUserid(rs.getString(1));
			ub.setUsername(rs.getString(2));
			ub.setRole_name(rs.getString(3));
			ub.setEmail(rs.getString(4));
			ub.setPhone(rs.getString(5));

			results.add(ub);
		}
		con.close();
		return results;
	}

	public ArrayList<functionBean> getPermission(String username, String page) throws SQLException, NamingException {
		Connection con = new dbconnection().getConnection();
		ResultSet rs1 = null;
		ArrayList<functionBean> perm = new ArrayList<functionBean>();
		String query = "select f.fun_name, f.fun_url, p.page_name from function f, page p, page_functions pf, role r, user u where f.fun_id=pf.fun_id and p.page_id=pf.page_id and r.role_id = pf.role_id and u.role_id=r.role_id and p.page_url=? and u.username=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setObject(1, page);
		ps.setObject(2, username);
		rs1 = ps.executeQuery();

		while (rs1.next()) {
			functionBean fb = new functionBean();
			fb.setFun_name(rs1.getString("fun_name"));
			fb.setFun_url(rs1.getString("fun_url"));
			fb.setPage_name(rs1.getString("page_name"));

			perm.add(fb);
		}
		con.close();
		return perm;
	}

	public boolean updateEmp(String user_id, String user_name, String role_id, String email, String phone)
			throws SQLException, NamingException {
		int result = 0;
		Connection con = new dbconnection().getConnection();
		String query = "UPDATE user SET role_id = ?, username = ?, email = ?, phone = ? WHERE user_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setObject(1, role_id);
		ps.setObject(2, user_name);
		ps.setObject(3, email);
		ps.setObject(4, phone);
		ps.setObject(5, user_id);
		result = ps.executeUpdate();
		con.close();
		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getUserRole(String role_id) throws SQLException, NamingException {
		String result = null;
		Connection con = new dbconnection().getConnection();
		String query = "select role_name from role WHERE role_id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setObject(1, role_id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result = rs.getString(1);
		} else {
			result = null;
		}
		con.close();
		return result;
	}

	public String getRoleID(String role_name) throws SQLException, NamingException {
		String result = null;
		Connection con = new dbconnection().getConnection();
		String sql = "select role_id from role WHERE role_name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, role_name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result = rs.getString(1);
		} else {
			result = null;
		}
		con.close();
		return result;
	}

	public boolean deleteEmp(String user_id) throws SQLException, NamingException {
		int result = 0;
		Connection con = new dbconnection().getConnection();
		String sql = "DELETE FROM user WHERE user_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, user_id);
		result = ps.executeUpdate();
		con.close();
		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	public userBean getDetails(String user_id) throws SQLException, NamingException {
		userBean ub = new userBean();
		Connection con = new dbconnection().getConnection();
		ResultSet rs = null;
		String role_id = null;
		String username = null;
		String email = null;
		String phone = null;
		String sql = "select username, role_id, email, phone from user WHERE user_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, user_id);
		rs = ps.executeQuery();
		if (rs.next()) {
			username = rs.getString(1);
			role_id = rs.getString(2);
			email = rs.getString("email");
			phone = rs.getString("phone");
		}
		ub.setRole_name(new employeesDao().getUserRole(role_id));
		ub.setUsername(username);
		ub.setRole_id(role_id);
		ub.setEmail(email);
		ub.setPhone(phone);

		con.close();
		return ub;
	}

	public ArrayList<String> getRoles() throws SQLException, NamingException {
		ArrayList<String> result = new ArrayList<String>();
		Connection con = new dbconnection().getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select role_name from role");
		while (rs.next()) {
			result.add(rs.getString(1));
		}
		con.close();
		return result;
	}
}
