package com.safvan.exception.rest.booking;

import lombok.Data;

@Data
public class ApiNoEnoughSeatsForBooking extends ApiBookingException {

	public ApiNoEnoughSeatsForBooking(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
