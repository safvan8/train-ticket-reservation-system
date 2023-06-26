package com.safvan.exception;

public class TrainNotFoundException extends TrainException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrainNotFoundException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
