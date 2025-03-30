package com.assignment.splitwise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SigninRequest {

	@NotNull(message = "Please enter an email")
	@NotBlank(message = "Please enter an email")
	private String email;

	@NotNull(message = "Please enter a password")
	@NotBlank(message = "Please enter a password")
	private String password;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
