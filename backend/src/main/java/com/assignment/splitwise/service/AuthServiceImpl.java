package com.assignment.splitwise.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.splitwise.dto.AuthResponse;
import com.assignment.splitwise.entity.User;
import com.assignment.splitwise.exception.AuthException;
import com.assignment.splitwise.repository.UserRepository;
import com.assignment.splitwise.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	JwtUtil jwt;

	@Autowired
	UserRepository userRepository;

	@Override
	public AuthResponse authenticate(User user) {
		String token = this.jwt.generateToken(user.getId());
		return new AuthResponse(token);
	}

	@Override
	public User getUser(HttpServletRequest request) {
		try {
			String token = request.getHeader("Authorization");
			if (token == null) throw new AuthException("Authorization token required");
			token = token.split(" ")[1];
			UUID userId = this.jwt.getUserIdFromToken(token);
			return this.userRepository.findById(userId).orElseThrow(() -> new Exception());
		} catch (Exception e) {
			if (e instanceof AuthException) throw new AuthException(e.getMessage());
			else throw new AuthException("Invalid token");
		}
	}

}
