package com.safvan.exception;

public class NoEnoughSeatsForBooking extends BookingException {

	public NoEnoughSeatsForBooking(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
