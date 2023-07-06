package com.safvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.User;
import com.safvan.constants.UserRole;
import com.safvan.exception.login.UserNotFoundException;
import com.safvan.repository.IUserRepository;
import com.safvan.service.IUserManagementService;

/**
 * The UserManagementServiceImpl class implements the IUserManagementService
 * interface. It provides methods for managing user-related operations, such as
 * retrieving a user by ID and registering a new customer.
 *
 * @Author Safvan
 * @Version 1.0
 * @Since 1.0
 */
@Service
public class UserManagementServiceImpl implements IUserManagementService {

	@Autowired
	private IUserRepository userRepository;

	/**
	 * Retrieves a user based on the provided user ID.
	 *
	 * @param userId The ID of the user.
	 * @return The User object representing the user.
	 * @throws UserNotFoundException If the user is not found with the given ID.
	 */
	@Override
	public User getUserById(Integer userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
	}

	/**
	 * Registers a new customer by saving the user object to the database.
	 *
	 * @param user The User object representing the new customer.
	 * @return The registered User object.
	 */
	@Override
	public User registerNewCustomer(User user) {
		// Set the role of the user to CUSTOMER
		user.setRole(UserRole.CUSTOMER);
		User result = userRepository.save(user);
		return result;
	}
}
