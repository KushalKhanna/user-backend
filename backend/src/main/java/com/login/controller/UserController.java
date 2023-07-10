package com.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.login.model.User;
import com.login.repository.UserRepository;
import com.login.service.UserService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://10.0.2.16:8081")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
//	TESTED
	@PostMapping("/add-user")
	public String addUser(@RequestBody JsonNode user) {
		return userService.addUser(user);
	}
	
//	TESTED
	@PostMapping("/login")
	private String signIn(@RequestBody JsonNode login) {
		return userService.signIn(login);
	}
	
	@GetMapping("/forgot-password/{username}")
	private String forgotPassword(@PathVariable String username) {
		System.out.println(username.getClass());
		return userService.forgotPassword(username);
	}
	
	@PostMapping("/update-password")
	private String changePassword(@RequestBody JsonNode userDetails) {
		return userService.changePassword(userDetails);
	}
	
	@GetMapping("/all-users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
}
