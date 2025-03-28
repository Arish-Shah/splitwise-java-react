package com.assignment.splitwise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupRequest {

	private String name;

	@NotNull(message = "Email is required")
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Enter a valid email")
	private String email;

	@NotNull(message = "Password is required")
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 4, message = "Password must be at least 4 characters")
	private String password;

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

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
