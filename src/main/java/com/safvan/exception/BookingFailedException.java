package com.safvan.exception;

public class BookingFailedException extends BookingException {
	public BookingFailedException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
