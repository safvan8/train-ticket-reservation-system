package com.safvan.exception.mvc.train;

import lombok.Data;

@Data
public class TrainNotFoundException extends TrainException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrainNotFoundException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
