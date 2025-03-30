package com.assignment.splitwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.splitwise.dto.UserResponse;
import com.assignment.splitwise.entity.User;
import com.assignment.splitwise.service.AuthService;
import com.assignment.splitwise.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "${splitwise.frontend.url}")
@RestController
@RequestMapping("${splitwise.api.base-path}/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;

	@GetMapping("/search")
	ResponseEntity<List<UserResponse>> search(@RequestParam(defaultValue = "") String q, HttpServletRequest request) {
		User me = this.authService.getUser(request);
		List<UserResponse> response = this.userService.search(q, me);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
