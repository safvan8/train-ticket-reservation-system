package com.safvan.exception.restapi.booking;

import lombok.Data;

@Data
public class ApiBookingFailedException extends ApiBookingException {

	public ApiBookingFailedException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
