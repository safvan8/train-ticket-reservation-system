package com.safvan.exception.login;

public class UserNotFoundException extends LoginFailedException {
	public UserNotFoundException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
