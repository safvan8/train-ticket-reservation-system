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
public class ApiBookingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private StackTraceElement[] stackTrace;
	private String userFriendlyMessage;

	public ApiBookingException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(userFriendlyMessage);
		this.stackTrace = stackTrace;
		this.userFriendlyMessage = userFriendlyMessage;
	}
}
