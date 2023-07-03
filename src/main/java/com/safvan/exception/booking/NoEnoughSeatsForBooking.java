package com.safvan.exception.booking;

public class NoEnoughSeatsForBooking extends BookingException {

	public NoEnoughSeatsForBooking(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
