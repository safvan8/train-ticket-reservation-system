package com.safvan.exception.train;

public class TrainException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrainException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
