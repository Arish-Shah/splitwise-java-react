package com.assignment.splitwise.service;

import com.assignment.splitwise.dto.AuthResponse;
import com.assignment.splitwise.entity.User;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
	abstract AuthResponse authenticate(User user);
	abstract User getUser(HttpServletRequest request);
}
