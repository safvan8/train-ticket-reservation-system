package com.safvan.exception.mvc.login;

import lombok.Data;

@Data
public class LoginFailedException extends RuntimeException {

	private StackTraceElement[] stackTrace;
	private String userFriendlyMessage;

	public LoginFailedException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(userFriendlyMessage);
		this.stackTrace = stackTrace;
		this.userFriendlyMessage = userFriendlyMessage;
	}
}
