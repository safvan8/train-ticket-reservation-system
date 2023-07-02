package com.safvan.exception.booking;

public class BookingFailedException extends BookingException {

	public BookingFailedException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
