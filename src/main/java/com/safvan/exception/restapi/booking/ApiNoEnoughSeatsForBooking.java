package com.safvan.exception.restapi.booking;

import lombok.Data;

@Data
public class ApiNoEnoughSeatsForBooking extends ApiBookingException {

	public ApiNoEnoughSeatsForBooking(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
