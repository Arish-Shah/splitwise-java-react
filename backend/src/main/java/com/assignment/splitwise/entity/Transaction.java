package com.assignment.splitwise.entity;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.assignment.splitwise.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "requester_id", nullable = false)
	private User requester;

	@ManyToOne
	@JoinColumn(name = "payer_id", nullable = false)
	private User payer;

	@Column(nullable = false)
	private Double amount;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "status", nullable = false)
	private PaymentStatus status = PaymentStatus.PENDING;

	@Column
	private String description;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	public Transaction() {}

	public Transaction(User requester, User payer, Double amount, String description) {
		this.requester = requester;
		this.payer = payer;
		this.amount = amount;
		this.description = description;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getRequester() {
		return this.requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getPayer() {
		return this.payer;
	}

	public void setPayer(User payer) {
		this.payer = payer;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentStatus getStatus() {
		return this.status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
