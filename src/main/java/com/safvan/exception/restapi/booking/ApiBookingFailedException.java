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
public class ApiBookingFailedException extends ApiBookingException {

	public ApiBookingFailedException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
