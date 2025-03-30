package com.assignment.splitwise.dto;

import java.util.UUID;

import com.assignment.splitwise.entity.Transaction;
import com.assignment.splitwise.entity.User;
import com.assignment.splitwise.enums.PaymentStatus;

public class TransactionResponse {

	private UUID id;

	private Double amount;

	private UserResponse requester;
	
	private UserResponse payer;

	private PaymentStatus status;

	public TransactionResponse() {}

	public TransactionResponse(UUID id, Double amount, UserResponse payer) {
		this.id = id;
		this.amount = amount;
		this.payer = payer;
	}

	public TransactionResponse(Transaction transaction, User requester, User payer) {
		this.id = transaction.getId();
		this.amount = transaction.getAmount();
		this.requester = new UserResponse(requester);
		this.payer = new UserResponse(payer);
		this.status = transaction.getStatus();
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public UserResponse getRequester() {
		return this.requester;
	}

	public void setRequester(UserResponse requester) {
		this.requester = requester;
	}

	public UserResponse getPayer() {
		return this.payer;
	}

	public void setPayer(UserResponse payer) {
		this.payer = payer;
	}

	public PaymentStatus getStatus() {
		return this.status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

}
