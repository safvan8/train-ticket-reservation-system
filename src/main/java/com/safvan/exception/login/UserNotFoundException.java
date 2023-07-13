package com.safvan.exception.login;

import lombok.Data;

@Data
public class UserNotFoundException extends LoginFailedException {
	public UserNotFoundException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
