package com.safvan.service.mvc;

import com.safvan.beans.User;
import com.safvan.exception.mvc.login.UserNotFoundException;

/**
 * The ILoginManagementService interface defines methods for user authentication
 * and session management.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
public interface ILoginManagementService {

	/**
	 * Authenticates a user based on the provided username and password.
	 *
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return The User object representing the authenticated user.
	 */
	public User authenticateUser(String username, String plainPassword);

	/**
	 * Stores the session ID for a user.
	 *
	 * @param userId    The ID of the user.
	 * @param sessionId The session ID to be stored.
	 * @throws ApiUserNotFoundException If the user is not found for the given ID.
	 */
	public void storeUserSession(Integer userId, String sessionId) throws UserNotFoundException;

	/**
	 * Retrieves the user based on the session ID.
	 *
	 * @param sessionId The session ID of the user.
	 * @return The User object representing the user.
	 */
	public User getUserbySessionId(String sessionId);

	/**
	 * used to hash a plain password uisng cryptographic algorithm.
	 * 
	 * @param plainPassword the human readble password
	 * @return the salted hash value corresponding to the password.
	 */
	public String hashPassword(String plainPassword);

	/**
	 * Compares a plain password with a hashed password to determine if they match.
	 *
	 * @param plainPassword  The plain password to be checked.
	 * @param hashedPassword The hashed password to be compared against.
	 * @return True if the plain password matches the hashed password, false
	 *         otherwise.
	 */
	public boolean isPasswordHashMatching(String plainPassword, String hashedPassword);
}