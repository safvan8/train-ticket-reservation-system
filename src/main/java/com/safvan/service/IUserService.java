package com.safvan.service;

import com.safvan.beans.User;

public interface IUserService {

	public User authenticateUser(String username, String password);
}
