package com.acl.userBean;

import java.util.HashMap;

public class userBean {
	private String username = null;
	private String password = null;
	private String type = null;
	private HashMap<String, String> func = new HashMap<String, String>();
	
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
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFunc(HashMap<String, String> func) {
		this.func = func;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public HashMap<String, String> getFunc() {
		return func;
	}
}
