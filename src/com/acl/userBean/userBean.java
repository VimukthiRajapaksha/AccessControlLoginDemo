package com.acl.userBean;

import java.util.HashMap;

//u.user_id, u.username, r.role_name
public class userBean {
	private String username = null;
	private String role_id = null;
	private String email = null;
	private String phone = null;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String userid = null;
	private String role_name = null;
	private String password = null;
	private String type = null;
	private HashMap<String, String> pages = new HashMap<String, String>();
	private HashMap<String, String> functions = new HashMap<String, String>();

	public userBean() {
		// TODO Auto-generated constructor stub
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public userBean(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HashMap<String, String> getFunctions() {
		return functions;
	}

	public void setFunctions(HashMap<String, String> functions) {
		this.functions = functions;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPages(HashMap<String, String> func) {
		this.pages = func;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public HashMap<String, String> getPages() {
		return pages;
	}
}
