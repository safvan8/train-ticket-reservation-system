package com.safvan.exception.booking;

import lombok.Data;

@Data
public class NoEnoughSeatsForBooking extends BookingException {

	public NoEnoughSeatsForBooking(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
