package com.safvan.service;

import com.safvan.beans.User;
import com.safvan.exception.login.UserNotFoundException;

/**
 * The ILoginManagementService interface defines methods for user authentication
 * and session management.
 *
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
public interface ILoginManagementService {

	/**
	 * Authenticates a user based on the provided username and password.
	 *
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return The User object representing the authenticated user.
	 */
	public User authenticateUser(String username, String password);

	/**
	 * Stores the session ID for a user.
	 *
	 * @param userId    The ID of the user.
	 * @param sessionId The session ID to be stored.
	 * @throws UserNotFoundException If the user is not found for the given ID.
	 */
	public void storeUserSession(Integer userId, String sessionId) throws UserNotFoundException;

	/**
	 * Retrieves the user based on the session ID.
	 *
	 * @param sessionId The session ID of the user.
	 * @return The User object representing the user.
	 */
	public User getUserbySessionId(String sessionId);
}
