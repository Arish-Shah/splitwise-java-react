package com.assignment.splitwise.dto;

import com.assignment.splitwise.entity.User;

public class UserResponse {

	private String name;

	private String email;
	
	public UserResponse(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public UserResponse(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
