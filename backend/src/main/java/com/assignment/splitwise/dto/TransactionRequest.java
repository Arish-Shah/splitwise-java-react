package com.assignment.splitwise.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionRequest {
	
	@NotBlank(message = "Payer email is required")
	@NotNull(message = "Payer email is required")
	@Email(message = "Please check payer email")
	private String email;

	@DecimalMin(value = "0.01", message = "Amount cannot be less than 1 cent")
	private Double amount;

	private String description;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
