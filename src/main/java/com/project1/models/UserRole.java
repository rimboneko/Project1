package com.project1.models;

import java.io.Serializable;

public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;
	private int user_role_id;
	private String user_role;

	public UserRole(int user_role_id, String user_role) {
		super();
		this.user_role_id = user_role_id;
		this.user_role = user_role;
	}
	
	public UserRole(String user_role) {
		super();
		this.user_role = user_role;
	}

	public int getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	@Override
	public String toString() {
		return "UserRole [user_role_id=" + user_role_id + ", user_role=" + user_role + "]";
	}
	
}
