package com.safvan.exception.booking;

import lombok.Data;

@Data
public class BookingFailedException extends BookingException {

	public BookingFailedException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
