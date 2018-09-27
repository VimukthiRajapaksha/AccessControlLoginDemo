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
	
	public String validateUser(userBean bean){
		Connection con = new dbconnection().getConnection();
		username = bean.getUsername();
		password = bean.getPassword();
		String role_id = null;
		String sql = "SELECT * FROM user WHERE username =? and password =?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, username);
			ps.setObject(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				role_id =rs.getString("role_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return role_id;
	}
	public String md5(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
	}
	
	public HashMap<String, String> getPages(String role_id){
		Connection con = new dbconnection().getConnection();
		String sql = "select p.page_name, p.page_url FROM page p, role_pages pf WHERE p.page_id=pf.page_id and pf.role_id=?";
		HashMap<String, String> func = new HashMap<>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role_id);
			ResultSet rs = ps.executeQuery();
			
			/*st = con.createStatement();
			ResultSet rs = st.executeQuery("select p.page_name, p.page_url FROM page p, user u, user_pages up WHERE p.page_id=up.page_id and u.user_id=up.user_id and u.role_id ='"+role_id+"'");*/
			while(rs.next()) {
				func.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return func;
	}
	public HashMap<String, String> getFunctions(String role_id, String page){
		Connection con = new dbconnection().getConnection();
		String sql = "select fun_name, fun_url from function f, page p, page_functions pf where f.fun_id=pf.fun_id and pf.role_id=? and p.page_id=pf.page_id and p.page_name=?";
		HashMap<String, String> func = new HashMap<String, String>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role_id);
			ps.setObject(2, page);
			ResultSet rs = ps.executeQuery();
			/*st = con.createStatement();
			ResultSet rs = st.executeQuery("select fun_name, fun_url from function f, page p, page_functions pf where f.fun_id=pf.fun_id and pf.role_id='"+role_id+"' and p.page_id=pf.page_id and p.page_name='"+page+"'");*/
			while(rs.next()) {
				func.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return func;
	}
}
