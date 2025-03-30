package com.assignment.splitwise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.splitwise.dto.SigninRequest;
import com.assignment.splitwise.dto.SignupRequest;
import com.assignment.splitwise.dto.UserResponse;
import com.assignment.splitwise.entity.User;
import com.assignment.splitwise.exception.SplitwiseException;
import com.assignment.splitwise.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User createUser(SignupRequest signupRequest) {
		Optional<User> existingUser = this.userRepository.findByEmail(signupRequest.getEmail());

		if (existingUser.isPresent()) throw new SplitwiseException("Email is already taken");

		String password = this.passwordEncoder.encode(signupRequest.getPassword());
		signupRequest.setPassword(password);

		User user = new User(signupRequest.getName(), signupRequest.getEmail(), signupRequest.getPassword());
		return userRepository.save(user);
	}

	@Override
	public User verifyUser(SigninRequest signinRequest) {
		User user = this.userRepository
			.findByEmail(signinRequest.getEmail())
			.orElseThrow(() -> new SplitwiseException("Email or Password is incorrect"));

		boolean passwordMatch = this.passwordEncoder.matches(signinRequest.getPassword(), user.getPassword());
		if (!passwordMatch) throw new SplitwiseException("Email or Password is incorrect");

		return user;
	}

	@Override
	public List<UserResponse> search(String query, User me) {
		List<User> users = this.userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
		List<UserResponse> usersResponse = new ArrayList<>();

		for (var user: users) {
			if (!user.getId().equals(me.getId())) usersResponse.add(new UserResponse(user));
		}

		return usersResponse;
	}

}
