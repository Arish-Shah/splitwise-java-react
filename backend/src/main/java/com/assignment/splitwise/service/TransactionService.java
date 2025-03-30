package com.assignment.splitwise.service;

import com.assignment.splitwise.dto.TransactionResponse;

import java.util.List;

import com.assignment.splitwise.dto.TransactionRequest;
import com.assignment.splitwise.entity.User;

public interface TransactionService {
	abstract TransactionResponse add(TransactionRequest transactionRequest, User requester);
	abstract List<TransactionResponse> getAll(User user);
	abstract void update(String transactionId, User payer);
}
