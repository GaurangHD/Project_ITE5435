package com.n01579298.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n01579298.order_service.model.User;
import com.n01579298.order_service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
