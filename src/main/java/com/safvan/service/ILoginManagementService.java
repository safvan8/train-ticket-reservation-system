package com.safvan.service;

import com.safvan.beans.User;

public interface ILoginManagementService {

	public User authenticateUser(String username, String password);

	public void storeUserSession(Integer userId, String sessionId);
}