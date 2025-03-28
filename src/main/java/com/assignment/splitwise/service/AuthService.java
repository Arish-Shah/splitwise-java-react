package com.assignment.splitwise.service;

import com.assignment.splitwise.dto.AuthResponse;

import com.assignment.splitwise.entity.User;

public interface AuthService {
	abstract AuthResponse authenticate(User user);
}
