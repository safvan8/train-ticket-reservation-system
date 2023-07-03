package com.safvan.exception.booking;

public class BookingException extends RuntimeException {

	public BookingException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
