package com.assignment.splitwise.service;

import com.assignment.splitwise.dto.SigninRequest;
import com.assignment.splitwise.dto.SignupRequest;
import com.assignment.splitwise.entity.User;

public interface UserService {
	abstract User createUser(SignupRequest signupRequest);
	abstract User verifyUser(SigninRequest signinRequest);
}
