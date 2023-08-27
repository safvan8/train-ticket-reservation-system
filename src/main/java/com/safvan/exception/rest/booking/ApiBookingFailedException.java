package com.safvan.exception.rest.booking;

import lombok.Data;

@Data
public class ApiBookingFailedException extends ApiBookingException {

	public ApiBookingFailedException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
