package com.safvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.User;
import com.safvan.exception.login.UserNotFoundException;
import com.safvan.repository.IUserRepository;
import com.safvan.service.ILoginManagementService;
import com.safvan.service.IUserManagementService;

/**
 * The LoginManagementServiceImpl class provides implementations for user
 * authentication and session management.
 *
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
@Service
public class LoginManagementServiceImpl implements ILoginManagementService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserManagementService userManagementService;

	/**
	 * Authenticates a user based on the provided username and password.
	 *
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return The User object representing the authenticated user.
	 */
	@Override
	public User authenticateUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	/**
	 * Stores the session ID for a user.
	 *
	 * @param userId    The ID of the user.
	 * @param sessionId The session ID to be stored.
	 * @throws UserNotFoundException If the user is not found for the given ID.
	 */
	@Override
	public void storeUserSession(Integer userId, String sessionId) {
		User user = userManagementService.getUserById(userId);
		if (user != null)
			userRepository.updateSessionIdByUserId(userId, sessionId);
		else
			throw new UserNotFoundException("User Not found for the Id: " + userId);
	}

	/**
	 * Retrieves the user based on the session ID.
	 *
	 * @param sessionId The session ID of the user.
	 * @return The User object representing the user.
	 */
	@Override
	public User getUserbySessionId(String sessionId) {
		User user = userRepository.findBySessionId(sessionId);
		return user;
	}
}
