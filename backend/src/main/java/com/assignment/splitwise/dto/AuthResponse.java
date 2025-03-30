package com.assignment.splitwise.dto;

public class AuthResponse {

	private String token;

	private String name;
	
	private String email;

	public AuthResponse() {
	}

	public AuthResponse(String token, String name, String email) {
		this.token = token;
		this.name = name;
		this.email = email;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
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
