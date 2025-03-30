package com.assignment.splitwise.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.splitwise.entity.Transaction;
import com.assignment.splitwise.entity.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
	List<Transaction> findAllByRequesterOrPayerOrderByCreatedAtDesc(User requester, User payer);
}
