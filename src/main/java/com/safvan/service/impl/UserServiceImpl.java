package com.safvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safvan.beans.User;
import com.safvan.repository.IUserRepository;
import com.safvan.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User authenticateUser(String username, String password) {

		return userRepository.findByUsernameAndPassword(username, password);
	}

}
