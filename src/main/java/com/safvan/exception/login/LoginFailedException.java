package com.safvan.exception.login;

public class LoginFailedException extends RuntimeException {

	public LoginFailedException(String customExceptionMesage) {
		super(customExceptionMesage);
	}
}
