package com.safvan.exception.restapi.booking;

import lombok.Data;
/**
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 *
 */
@Data
public class ApiNoEnoughSeatsForBooking extends ApiBookingException {

	public ApiNoEnoughSeatsForBooking(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
