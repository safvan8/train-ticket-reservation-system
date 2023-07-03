package com.safvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.User;
import com.safvan.constants.UserRole;
import com.safvan.exception.login.UserNotFoundException;
import com.safvan.repository.IUserRepository;
import com.safvan.service.IUserManagementService;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User getUserById(Integer userId) {

		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not found with the id:" + userId));
	}

	@Override
	public User registerNewCustomer(User user) {

		// setting cutomer role
		user.setRole(UserRole.CUSTOMER);
		User result = userRepository.save(user);

		return result;
	}

}
