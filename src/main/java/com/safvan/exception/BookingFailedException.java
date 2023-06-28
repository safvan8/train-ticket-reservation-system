package com.safvan.exception;

public class BookingFailedException extends RuntimeException {
	public BookingFailedException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
