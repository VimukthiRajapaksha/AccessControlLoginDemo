package com.acl.userDao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.acl.dbconnection.dbconnection;
import com.acl.userBean.userBean;

public class userDao {
	Connection con = new dbconnection().getConnection();
	private String username = null;
	private String password = null;
	
	public String validateUser(userBean bean){
		username = bean.getUsername();
		password = bean.getPassword();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE username ='"+username+"' and password ='"+password+"'");
			
			if(rs.next()) {
				return rs.getString("role_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return null;
	}
	public String validatePage(String roleId){
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE username ='"+username+"' and password ='"+password+"'");
			
			if(rs.next()) {
				return rs.getString("role_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return null;
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
	
	public HashMap<String, String> setmap(String role_id){
		Statement st;
		HashMap<String, String> func = new HashMap<>();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select p.page_name, p.page_url FROM page p, user u, user_pages up WHERE p.page_id=up.page_id and u.user_id=up.user_id and u.role_id ='"+role_id+"'");
			while(rs.next()) {
				func.put(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return func;
	}
}
