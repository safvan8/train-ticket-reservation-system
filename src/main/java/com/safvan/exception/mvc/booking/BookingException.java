package com.safvan.exception.mvc.booking;

import lombok.Data;

@Data
public class BookingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private StackTraceElement[] stackTrace;
	private String userFriendlyMessage;

	public BookingException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(userFriendlyMessage);
		this.stackTrace = stackTrace;
		this.userFriendlyMessage = userFriendlyMessage;
	}
}
