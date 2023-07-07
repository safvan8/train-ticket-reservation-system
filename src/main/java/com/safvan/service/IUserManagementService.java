package com.safvan.service;

import com.safvan.beans.User;

/**
 * The IUserManagementService interface defines methods for managing
 * user-related operations.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 */
public interface IUserManagementService {

	/**
	 * Retrieves a user based on the provided user ID.
	 *
	 * @param userId The ID of the user.
	 * @return The User object representing the user.
	 * @throws UserNotFoundException If the user is not found with the given ID.
	 */
	public User getUserById(Integer userId);

	/**
	 * Registers a new customer by saving the user object to the database.
	 *
	 * @param user The User object representing the new customer.
	 * @return The registered User object.
	 */
	public User registerNewCustomer(User user);
}
