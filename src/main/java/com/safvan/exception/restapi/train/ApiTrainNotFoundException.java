package com.safvan.exception.restapi.train;

import lombok.Data;
/**
 * 
 * @author Safvan
 * @version 2.0
 * @since 2.0
 *
 */
@Data
public class ApiTrainNotFoundException extends ApiTrainException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiTrainNotFoundException(StackTraceElement[] stackTrace, String userFriendlyMessage) {
		super(stackTrace, userFriendlyMessage);
	}
}
