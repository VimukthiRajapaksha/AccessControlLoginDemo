package com.acl.userDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.acl.dbconnection.dbconnection;
import com.acl.userBean.userBean;

public class employeesDao {
	
	public ResultSet getView() {
		Connection con = new dbconnection().getConnection();
		ResultSet rs = null;
		try {
			String query = "select u.user_id, u.username, r.role_name, u.email, u.phone from user u, role r where u.role_id=r.role_id";
			PreparedStatement ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			//rs = st.executeQuery("select u.user_id, u.username, r.role_name, u.email, u.phone from user u, role r where u.role_id=r.role_id");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getPermission(String username, String page) {
		Connection con = new dbconnection().getConnection();
		ResultSet rs1 = null;
		try {
			String query = "select f.fun_name, f.fun_url, p.page_name from function f, page p, page_functions pf, role r, user u where f.fun_id=pf.fun_id and p.page_id=pf.page_id and r.role_id = pf.role_id and u.role_id=r.role_id and p.page_url=? and u.username=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, page);
			ps.setObject(2, username);
			rs1 = ps.executeQuery();
			//Statement st1 = con.createStatement();
			//rs1 = st1.executeQuery("select f.fun_name, f.fun_url, p.page_name from function f, page p, page_functions pf, role r, user u where f.fun_id=pf.fun_id and p.page_id=pf.page_id and r.role_id = pf.role_id and u.role_id=r.role_id and p.page_url='"+page+"' and u.username='"+username+"'");
			return rs1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs1;
	}
	public boolean updateEmp(String user_id, String user_name, String role_id, String email, String phone){
		int result=0;
		Connection con = new dbconnection().getConnection();
		try {
			String query = "UPDATE user SET role_id = ?, username = ?, email = ?, phone = ? WHERE user_id = ?";
			//Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, role_id);
			ps.setObject(2, user_name);
			ps.setObject(3, email);
			ps.setObject(4, phone);
			ps.setObject(5, user_id);
			result = ps.executeUpdate();
			//result = st.executeUpdate("UPDATE user SET role_id = '"+role_id+"', username = '"+user_name+"', email = '"+email+"', phone = '"+phone+"' WHERE user_id = '"+user_id+"'");
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
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public String getUserRole(String role_id){
		String result=null;
		Connection con = new dbconnection().getConnection();
		try {
			String query = "select role_name from role WHERE role_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setObject(1, role_id);
			ResultSet rs = ps.executeQuery();
			/*Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select role_name from role WHERE role_id='"+role_id+"'");*/
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
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public String getRoleID(String role_name){
		String result=null;
		Connection con = new dbconnection().getConnection();
		try {
			String sql = "select role_id from role WHERE role_name=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role_name);
			ResultSet rs = ps.executeQuery();
			/*Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select role_id from role WHERE role_name='"+role_name+"'");*/
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
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public boolean deleteEmp(String user_id){
		int result=0;
		Connection con = new dbconnection().getConnection();
		try {
			String sql = "DELETE FROM user WHERE user_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, user_id);
			result = ps.executeUpdate();
			/*Statement st = con.createStatement();
			result = st.executeUpdate("DELETE FROM user WHERE user_id='"+user_id+"'");*/
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
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public userBean getDetails(String user_id) {
		userBean ub = new userBean();
		Connection con = new dbconnection().getConnection();
		ResultSet rs = null;
		String role_id = null;
		String username = null;
		String email = null;
		String phone = null;
		try {
			String sql = "select username, role_id, email, phone from user WHERE user_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, user_id);
			rs = ps.executeQuery();
			/*Statement st = con.createStatement();
			rs = st.executeQuery("select username, role_id, email, phone from user WHERE user_id='"+user_id+"'");*/
			if(rs.next()) {
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
		return ub;
	}
	public ArrayList<String> getRoles() {
		ArrayList<String> result= new ArrayList<String>();
		Connection con = new dbconnection().getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select role_name from role");
			while(rs.next()) {
				result.add(rs.getString(1));
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
		return result;
	}
}
