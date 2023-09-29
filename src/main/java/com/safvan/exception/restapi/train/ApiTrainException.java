package com.safvan.exception.restapi.train;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 *
 */
@Data
@NoArgsConstructor
public class ApiTrainException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StackTraceElement[] stackTrace;
	private String userFriendlyMessage;

	public ApiTrainException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace.toString());
		this.stackTrace = stackTrace;
		this.userFriendlyMessage = userFriendlyMessage;
	}
}
