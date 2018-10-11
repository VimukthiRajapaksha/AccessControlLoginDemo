package com.acl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.acl.bean.FunctionBean;
import com.acl.bean.UserBean;
import com.acl.util.connection.DBConnection;

public class EmployeesDao {

	public ArrayList<UserBean> getView() throws SQLException, NamingException {
		return getView(7, 0);
	}
	public ArrayList<UserBean> getView(int limit, int offset) throws NamingException, SQLException{
		Connection con = null;
		ResultSet rs = null;
		ArrayList<UserBean> results = new ArrayList<UserBean>();
		
		try {
			con = new DBConnection().getConnection();
			String query = "select u.user_id, u.username, r.role_name, u.email, u.phone from user u, role r where u.role_id=r.role_id order by u.user_id limit ? offset ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, limit);
			ps.setObject(2, offset);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserBean ub = new UserBean();
				ub.setUserid(rs.getString(1));
				ub.setUsername(rs.getString(2));
				ub.setRole_name(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setPhone(rs.getString(5));

				results.add(ub);
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return results;
	}
	public String getEmpCount() throws NamingException, SQLException{
		Connection con = null;
		String query = "select count(user_id) from user";
		ResultSet rs = null;
		String count = null;
		try {
			con = new DBConnection().getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getString(1);
			}
		} finally {
			if(con!=null) {
				con.close();
			}
		}
		return count;
	}
	public ArrayList<UserBean> getSearchView(String key) throws SQLException, NamingException {
		Connection con = null;
		ResultSet rs = null;
		ArrayList<UserBean> results = new ArrayList<UserBean>();

		try {
			con = new DBConnection().getConnection();
			String query = "select u.user_id, u.username, r.role_name, u.email, u.phone from user u, role r where (u.user_id like ? or u.username like ? or r.role_name like ? or u.email like ? or u.phone like ?) and (u.role_id=r.role_id)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, "%" + key + "%");
			ps.setObject(2, "%" + key + "%");
			ps.setObject(3, "%" + key + "%");
			ps.setObject(4, "%" + key + "%");
			ps.setObject(5, "%" + key + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				UserBean ub = new UserBean();
				ub.setUserid(rs.getString(1));
				ub.setUsername(rs.getString(2));
				ub.setRole_name(rs.getString(3));
				ub.setEmail(rs.getString(4));
				ub.setPhone(rs.getString(5));

				results.add(ub);
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return results;
	}

	public ArrayList<FunctionBean> getPermission(String username, String page) throws SQLException, NamingException {
		Connection con = null;
		ResultSet rs1 = null;
		ArrayList<FunctionBean> perm = new ArrayList<FunctionBean>();
		String query = "select f.fun_name, f.fun_url, p.page_name from function f, page p, page_functions pf, role r, user u where f.fun_id=pf.fun_id and p.page_id=pf.page_id and r.role_id = pf.role_id and u.role_id=r.role_id and p.page_url=? and u.username=?";
		
		try {
			con = new DBConnection().getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, page);
			ps.setObject(2, username);
			rs1 = ps.executeQuery();

			while (rs1.next()) {
				FunctionBean fb = new FunctionBean();
				fb.setFun_name(rs1.getString("fun_name"));
				fb.setFun_url(rs1.getString("fun_url"));
				fb.setPage_name(rs1.getString("page_name"));

				perm.add(fb);
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return perm;
	}

	public boolean updateEmp(String user_id, String user_name, String role_id, String email, String phone)
			throws SQLException, NamingException {
		int result = 0;
		Connection con = null;
		String query = "UPDATE user SET role_id = ?, username = ?, email = ?, phone = ? WHERE user_id = ?";
		
		try {
			con = new DBConnection().getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, role_id);
			ps.setObject(2, user_name);
			ps.setObject(3, email);
			ps.setObject(4, phone);
			ps.setObject(5, user_id);
			result = ps.executeUpdate();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getUserRole(String role_id) throws SQLException, NamingException {
		String result = null;
		Connection con = null;
		String query = "select role_name from role WHERE role_id=?";
		
		try {
			con = new DBConnection().getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, role_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			} else {
				result = null;
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return result;
	}

	public String getRoleID(String role_name) throws SQLException, NamingException {
		String result = null;
		Connection con = null;
		String sql = "select role_id from role WHERE role_name=?";
		
		try {
			con = new DBConnection().getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role_name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			} else {
				result = null;
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return result;
	}

	public boolean deleteEmp(String user_id) throws SQLException, NamingException {
		int result = 0;
		Connection con = null;
		String sql = "DELETE FROM user WHERE user_id=?";
		
		try {
			con = new DBConnection().getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, user_id);
			result = ps.executeUpdate();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	public UserBean getDetails(String user_id) throws SQLException, NamingException {
		UserBean ub = new UserBean();
		Connection con = null;
		ResultSet rs = null;
		String role_id = null;
		String username = null;
		String email = null;
		String phone = null;
		String sql = "select username, role_id, email, phone from user WHERE user_id=?";
		
		try {
			con = new DBConnection().getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, user_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				username = rs.getString(1);
				role_id = rs.getString(2);
				email = rs.getString("email");
				phone = rs.getString("phone");
			}
			ub.setRole_name(new EmployeesDao().getUserRole(role_id));
			ub.setUsername(username);
			ub.setRole_id(role_id);
			ub.setEmail(email);
			ub.setPhone(phone);
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return ub;
	}

	public ArrayList<String> getRoles() throws SQLException, NamingException {
		ArrayList<String> result = new ArrayList<String>();
		Connection con = null;
		try {
			con = new DBConnection().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select role_name from role");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return result;
	}
}
