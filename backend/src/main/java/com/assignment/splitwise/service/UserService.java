package com.assignment.splitwise.service;

import java.util.List;

import com.assignment.splitwise.dto.SigninRequest;
import com.assignment.splitwise.dto.SignupRequest;
import com.assignment.splitwise.dto.UserResponse;
import com.assignment.splitwise.entity.User;

public interface UserService {
	abstract User createUser(SignupRequest signupRequest);
	abstract User verifyUser(SigninRequest signinRequest);
	abstract List<UserResponse> search(String query, User me);
}
