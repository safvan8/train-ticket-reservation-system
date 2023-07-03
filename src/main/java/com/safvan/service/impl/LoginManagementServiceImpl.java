package com.safvan.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.User;
import com.safvan.exception.login.LoginFailedException;
import com.safvan.exception.login.UserNotFoundException;
import com.safvan.repository.IUserRepository;
import com.safvan.service.ILoginManagementService;
import com.safvan.service.IUserManagementService;

@Service
public class LoginManagementServiceImpl implements ILoginManagementService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserManagementService userManagementService;

	@Override
	public User authenticateUser(String username, String password) {

		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public void storeUserSession(Integer userId, String sessionId) {
		// first get user by id
		User user = userManagementService.getUserById(userId);
		if (user != null)
			userRepository.updateSessionIdByUserId(userId, sessionId);
		else
			throw new UserNotFoundException("User Not found for the Id :" + userId);
	}

	@Override
	public User getUserbySessionId(String sessionId) {

		User user = userRepository.findBySessionId(sessionId);

		return user;
	}
}
