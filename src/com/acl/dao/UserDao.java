package com.acl.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;

import com.acl.bean.UserBean;
import com.acl.util.connection.DBConnection;

public class UserDao {

	private String username = null;
	private String password = null;

	public String validateUser(UserBean bean) throws SQLException, NamingException {
		Connection con = null;
		String role_id = null;
		try {
			con = new DBConnection().getConnection();
			username = bean.getUsername();
			password = bean.getPassword();
			String sql = "SELECT * FROM user WHERE username =? and password =?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setObject(1, username);
			ps.setObject(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				role_id = rs.getString("role_id");
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return role_id;
	}

	public String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = null;

		md = MessageDigest.getInstance("MD5");

		byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public HashMap<String, String> getPages(String role_id) throws SQLException, NamingException {
		Connection con = null;
		HashMap<String, String> func = new HashMap<>();
		try {
			con = new DBConnection().getConnection();
			String sql = "select p.page_name, p.page_url FROM page p, role_pages pf WHERE p.page_id=pf.page_id and pf.role_id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				func.put(rs.getString(1), rs.getString(2));
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return func;
	}

	public HashMap<String, String> getFunctions(String role_id, String page) throws SQLException, NamingException {
		HashMap<String, String> func = new HashMap<String, String>();
		Connection con = null;
		try {
			con = new DBConnection().getConnection();
			String sql = "select fun_name, fun_url from function f, page p, page_functions pf where f.fun_id=pf.fun_id and pf.role_id=? and p.page_id=pf.page_id and p.page_url=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role_id);
			ps.setObject(2, page);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				func.put(rs.getString(1), rs.getString(2));
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return func;
	}

	public HashMap<String, String> getAllPages() throws SQLException, NamingException {
		Connection con = null;
		HashMap<String, String> func = new HashMap<>();
		try {
			con = new DBConnection().getConnection();
			String sql = "select p.page_name, p.page_url FROM page p";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				func.put(rs.getString(1), rs.getString(2));
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return func;
	}

	public HashMap<String, String> getAllFunctions() throws SQLException, NamingException {
		Connection con = null;
		HashMap<String, String> func = new HashMap<String, String>();
		try {
			con = new DBConnection().getConnection();
			String sql = "select fun_name, fun_url from function";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				func.put(rs.getString(1), rs.getString(2));
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return func;
	}

	public boolean addUser(String type, String username, String password, String email, String phone)
			throws SQLException, NoSuchAlgorithmException, NamingException {
		int result = 0;
		Connection con = null;
		try {
			con = new DBConnection().getConnection();
			String query = "insert into user values(null, ? , ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setObject(1, type);
			ps.setObject(2, username);
			ps.setObject(3, new UserDao().md5(password));
			ps.setObject(4, email);
			ps.setObject(5, phone);

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
}
