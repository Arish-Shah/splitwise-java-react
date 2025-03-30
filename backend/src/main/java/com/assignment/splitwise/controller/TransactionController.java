package com.assignment.splitwise.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.splitwise.dto.TransactionResponse;
import com.assignment.splitwise.dto.TransactionRequest;
import com.assignment.splitwise.entity.User;
import com.assignment.splitwise.service.AuthService;
import com.assignment.splitwise.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin(origins = "${splitwise.frontend.url}")
@RestController
@RequestMapping("${splitwise.api.base-path}/transactions")
public class TransactionController {

	@Autowired
	AuthService authService;

	@Autowired
	TransactionService transactionService;

	Logger logger = LoggerFactory.getLogger(TransactionController.class);


	@GetMapping
	ResponseEntity<List<TransactionResponse>> getTransactions(HttpServletRequest request) {
		User user = this.authService.getUser(request);
		List<TransactionResponse> response = this.transactionService.getAll(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{transactionId}")
	ResponseEntity<Void> updateTransaction(@PathVariable(required = true) String transactionId, HttpServletRequest request) {
		User user = this.authService.getUser(request);
		this.transactionService.update(transactionId, user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	ResponseEntity<TransactionResponse> create(
		@Valid @RequestBody TransactionRequest transactionRequest,
		HttpServletRequest request
	) {
		User requester = this.authService.getUser(request);
		TransactionResponse response = this.transactionService.add(transactionRequest, requester);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
