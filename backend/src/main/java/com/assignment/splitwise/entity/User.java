package com.assignment.splitwise.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column
	private UUID id;

	@Column(length = 255, nullable = false)
	private String name;

	@Column(nullable = false, unique = true, length = 255)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "requester", cascade = CascadeType.ALL)
	private List<Transaction> sentTransactions;
	
	@OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
	private List<Transaction> receivedTransactions;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	public User() {
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public List<Transaction> getSentTransactions() {
		return this.sentTransactions;
	}

	public void setSentTransaction(List<Transaction> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}
	
	public List<Transaction> getReceivedTransactions() {
		return this.receivedTransactions;
	}

	public void setReceivedTransaction(List<Transaction> receivedTransactions) {
		this.receivedTransactions = receivedTransactions;
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
