package com.assignment.splitwise.dto;

import java.util.List;

import com.assignment.splitwise.entity.Transaction;

public class TransactionsResponse {

	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransaction(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
