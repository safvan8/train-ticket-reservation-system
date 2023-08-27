package com.safvan.exception.mvc.train;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StackTraceElement[] stackTrace;
	private String userFriendlyMessage;

	public TrainException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace.toString());
		this.stackTrace = stackTrace;
		this.userFriendlyMessage = userFriendlyMessage;
	}
}
