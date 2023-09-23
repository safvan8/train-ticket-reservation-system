package com.safvan.service.mvc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.safvan.beans.User;
import com.safvan.exception.mvc.login.UserNotFoundException;
import com.safvan.repository.mvc.IUserRepository;
import com.safvan.service.mvc.ILoginManagementService;
import com.safvan.service.mvc.IUserManagementService;
import com.safvan.util.CommonUtils;

/**
 * The LoginManagementServiceImpl class provides implementations for user
 * authentication and session management.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
@Service
public class LoginManagementServiceImpl implements ILoginManagementService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserManagementService userManagementService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Authenticates a user based on the provided username and password.
	 *
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return The User object representing the authenticated user.
	 */
	@Override
	public User authenticateUser(String username, String plainPassword) {

		User userFromDB = userRepository.findByUsername(username);

		// validating the password hash stored with user entered hashPassword
		boolean isPasswordMatched = isPasswordHashMatching(plainPassword, userFromDB.getPassword());

		if (isPasswordMatched)
			return userFromDB;

		return null;
	}

	/**
	 * Stores the session ID for a user.
	 *
	 * @param userId    The ID of the user.
	 * @param sessionId The session ID to be stored.
	 * @throws ApiUserNotFoundException If the user is not found for the given ID.
	 */
	@Override
	public void storeUserSession(Integer userId, String sessionId) {
		User user = userManagementService.getUserById(userId);
		
		// if user is not null,
		if (CommonUtils.isNotNull.test(user))
			userRepository.updateSessionIdByUserId(userId, sessionId);
		else {
			String userFriendlyMessage = "User Not found for the Id: " + userId;
			throw new UserNotFoundException(Thread.currentThread().getStackTrace(), userFriendlyMessage);
		}
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

	/**
	 * used to hash a plain password uisng cryptographic algorithm.
	 * 
	 * @param plainPassword the human readble password
	 * @return the salted hash value corresponding to the password.
	 */
	@Override
	public String hashPassword(String plainPassword) {

		return passwordEncoder.encode(plainPassword);
	}

	/**
	 * Compares a plain password with a hashed password to determine if they match.
	 *
	 * @param plainPassword  The plain password to be checked.
	 * @param hashedPassword The hashed password to be compared against.
	 * @return True if the plain password matches the hashed password, false
	 *         otherwise.
	 */
	@Override
	public boolean isPasswordHashMatching(String plainPassword, String hashedPassword) {

		return passwordEncoder.matches(plainPassword, hashedPassword);
	}
}
