package com.safvan.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.User;
import com.safvan.repository.IUserRepository;
import com.safvan.service.ILoginManagementService;


@Service
public class LoginManagementServiceImpl implements ILoginManagementService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User authenticateUser(String username, String password) {

		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public void storeUserSession(Integer userId, String sessionId) {
		// first get user by id
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setSessionId(sessionId);
			userRepository.save(user);
		}
		
	}

}
