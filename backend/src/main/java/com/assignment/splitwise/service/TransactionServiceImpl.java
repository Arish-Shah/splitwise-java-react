package com.assignment.splitwise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.splitwise.dto.TransactionResponse;
import com.assignment.splitwise.dto.TransactionRequest;
import com.assignment.splitwise.entity.Transaction;
import com.assignment.splitwise.entity.User;
import com.assignment.splitwise.enums.PaymentStatus;
import com.assignment.splitwise.exception.SplitwiseException;
import com.assignment.splitwise.repository.TransactionRepository;
import com.assignment.splitwise.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public TransactionResponse add(TransactionRequest transactionRequest, User requester) {
		try {
			User payer = userRepository.findByEmail(
				transactionRequest.getEmail()
			).orElseThrow(() -> new SplitwiseException("Payer not found"));

			if (payer.getEmail().equals(requester.getEmail())) {
				throw new SplitwiseException("Cannot request payment from yourself");
			}

			Transaction transaction = new Transaction(
				requester, payer, transactionRequest.getAmount(),
				transactionRequest.getDescription());

			transaction = transactionRepository.save(transaction);

			return new TransactionResponse(transaction, requester, payer);
		} catch (Exception e) {
			if (e instanceof SplitwiseException)
				throw new SplitwiseException(e.getMessage());
			else throw new SplitwiseException("Payer not found");
		}

	}

	@Override
	public List<TransactionResponse> getAll(User user) {
		List<Transaction> transactions = this.transactionRepository
			.findAllByRequesterOrPayerOrderByCreatedAtDesc(user, user);
		List<TransactionResponse> responseList = new ArrayList<>();

		for (var transaction: transactions) {
			TransactionResponse response = new TransactionResponse(
				transaction, transaction.getRequester(),
				transaction.getPayer());
			responseList.add(response);
		}

		return responseList;
	}

	@Override
	public void update(String transactionId, User payer) {
		Transaction transaction = this.transactionRepository
			.findById(UUID.fromString(transactionId))
			.orElseThrow(() -> new SplitwiseException("Transaction not found"));
		if (!transaction.getPayer().getId().equals(payer.getId()))
			throw new SplitwiseException("Unauthorized to update this transaction");

		transaction.setStatus(PaymentStatus.PAID);
		transactionRepository.save(transaction);
	}

}
