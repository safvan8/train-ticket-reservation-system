package com.safvan.exception;

public class BookingException extends RuntimeException {

	public BookingException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
