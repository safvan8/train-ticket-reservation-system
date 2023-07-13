package com.safvan.exception.booking;

import lombok.Data;

@Data
public class BookingException extends RuntimeException {

	private StackTraceElement[] stackTrace;
	private String userFriendlyMessage;

	public BookingException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(userFriendlyMessage);
		this.stackTrace = stackTrace;
		this.userFriendlyMessage = userFriendlyMessage;
	}
}
