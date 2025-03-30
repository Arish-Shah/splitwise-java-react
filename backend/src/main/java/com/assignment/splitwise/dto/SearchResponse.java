package com.assignment.splitwise.dto;

import java.util.List;

import com.assignment.splitwise.entity.User;

public class SearchResponse {

	private List<User> users;

	public SearchResponse() {
	}

	public SearchResponse(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
