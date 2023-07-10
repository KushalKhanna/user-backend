package com.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.model.User;
import com.login.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private String cleanString(String input) {
		return input.replaceAll("^\"|\"$", "");
	}

//	@Transactional(propagation=Propagation.REQUIRED)
	
	public String addUser(@RequestBody JsonNode user) {
		try {
//			objectMapper.writeValueAsString(user.get(u.getUsername()));
			User u = new User();
			u.setUsername(cleanString(user.get("username").toString()));
			u.setEmail(cleanString(user.get("email").toString()));
			u.setPassword(cleanString(user.get("password").toString()));
			User addedUser = userRepository.save(u);
			return "User added successfully!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Some error occurred in signing up!";
	}
	
	public List<User> getAllUsers() {
		List<User> allUsers;
		try {
			allUsers = userRepository.findAll();
			return allUsers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public String signIn(JsonNode login) {
		try {
			String username = cleanString(login.get("username").toString());
			String password = cleanString(login.get("password").toString());
			User loginUser = userRepository.findByUsername(username);

			if(loginUser == null) {
				return "No user exists by this username";
			}
			
//			WRONG
//			if(password. == loginUser.getPassword()) {
//				return "Incorrect password!";
//			}
			if(!password.equals(loginUser.getPassword())) {
				return "Incorrect password!";
			}
					
			return "Login Successfull";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Login unsuccessfull";
	}

	public String forgotPassword(String username) {
		System.out.println(username);
		User loginUser = userRepository.findByUsername(username);
		System.out.println(loginUser);
		if(loginUser == null) {
			return "No user exists by this username";
		}
		return "PROCEED!";
	}
	
	public String changePassword(JsonNode userDetails) {
//		CHECK FOR VALIDATION OF THIS BY EMAIL
		try {
			User exisitngUser = userRepository.findByUsername(cleanString(userDetails.get("username").toString()));
			exisitngUser.setId(exisitngUser.getId());
			exisitngUser.setPassword(cleanString(userDetails.get("newPassword").toString()));
			userRepository.save(exisitngUser);
			return "Password changed successfully!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error in updating password!";
	}
	
	
	
//	public String forgotPassword(String)
	
}
