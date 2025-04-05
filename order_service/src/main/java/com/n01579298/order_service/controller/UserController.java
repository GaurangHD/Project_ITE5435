package com.n01579298.order_service.controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.n01579298.order_service.model.User;
import com.n01579298.order_service.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public static User loginUser = null;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String home(Model model) {
		return "login";
	}
	
	@PostMapping
	public String login(@RequestParam String userName, @RequestParam String password) {
		User user = userService.getUserByUserName(userName);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				loginUser = user;
				return "redirect:/order";
			}
		}
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@RequestParam String userName, @RequestParam String password, @RequestParam String confirmPassword) {
		if(password.equals(confirmPassword)) {
			User newUser = new User();
			newUser.setUserName(userName);
			newUser.setPassword(password);
			newUser.setCreatedAt(LocalDateTime.now());
			userService.createUser(newUser);
		}else {
			return "redirect:/register";
		}
		return "redirect:/";
	}
}