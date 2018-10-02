package com.acl.userDao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.acl.dbconnection.dbconnection;
import com.acl.userBean.userBean;

public class userDao {

	private String username = null;
	private String password = null;

	public String validateUser(userBean bean) throws SQLException {
		Connection con = new dbconnection().getConnection();
		username = bean.getUsername();
		password = bean.getPassword();
		String role_id = null;
		String sql = "SELECT * FROM user WHERE username =? and password =?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, username);
		ps.setObject(2, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			role_id = rs.getString("role_id");
		}
		con.close();

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

	public HashMap<String, String> getPages(String role_id) throws SQLException {
		Connection con = new dbconnection().getConnection();
		String sql = "select p.page_name, p.page_url FROM page p, role_pages pf WHERE p.page_id=pf.page_id and pf.role_id=?";
		HashMap<String, String> func = new HashMap<>();

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, role_id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			func.put(rs.getString(1), rs.getString(2));
		}
		con.close();
		return func;
	}

	public HashMap<String, String> getFunctions(String role_id, String page) throws SQLException {
		Connection con = new dbconnection().getConnection();
		String sql = "select fun_name, fun_url from function f, page p, page_functions pf where f.fun_id=pf.fun_id and pf.role_id=? and p.page_id=pf.page_id and p.page_name=?";
		HashMap<String, String> func = new HashMap<String, String>();

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setObject(1, role_id);
		ps.setObject(2, page);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			func.put(rs.getString(1), rs.getString(2));
		}

		con.close();

		return func;
	}

	public HashMap<String, String> getAllPages() throws SQLException {
		Connection con = new dbconnection().getConnection();
		String sql = "select p.page_name, p.page_url FROM page p";
		HashMap<String, String> func = new HashMap<>();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			func.put(rs.getString(1), rs.getString(2));
		}
		con.close();
		return func;
	}

	public HashMap<String, String> getAllFunctions() throws SQLException {
		Connection con = new dbconnection().getConnection();
		String sql = "select fun_name, fun_url from function";
		HashMap<String, String> func = new HashMap<String, String>();

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			func.put(rs.getString(1), rs.getString(2));
		}
		con.close();
		return func;
	}
	public boolean addUser(String type, String username, String password, String email, String phone) throws SQLException, NoSuchAlgorithmException {
		int result = 0;
		Connection con = new dbconnection().getConnection();
		String query = "insert into user values(null, ? , ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setObject(1, type);
		ps.setObject(2, username);
		ps.setObject(3, new userDao().md5(password));
		ps.setObject(4, email);
		ps.setObject(5, phone);
		
		result = ps.executeUpdate();
		con.close();
		if(result!=0) {
			return true;
		}
		else {
			return false;
		}
	}
}
