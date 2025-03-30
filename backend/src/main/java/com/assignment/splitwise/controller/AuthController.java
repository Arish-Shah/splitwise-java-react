package com.assignment.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.splitwise.dto.AuthResponse;
import com.assignment.splitwise.dto.SigninRequest;
import com.assignment.splitwise.dto.SignupRequest;
import com.assignment.splitwise.entity.User;
import com.assignment.splitwise.service.AuthService;
import com.assignment.splitwise.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "${splitwise.frontend.url}")
@RestController
@RequestMapping("${splitwise.api.base-path}/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;

	@PostMapping("/signup")
	ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
		User user = this.userService.createUser(signupRequest);
		AuthResponse authResponse = this.authService.authenticate(user);
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	ResponseEntity<AuthResponse> signin(@Valid @RequestBody SigninRequest signinRequest) {
		User user = this.userService.verifyUser(signinRequest);
		AuthResponse authResponse = this.authService.authenticate(user);
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}

}
