package com.safvan.execption;

public class TrainNotFoundException extends TrainException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrainNotFoundException(String customExceptionMessage) {
		super(customExceptionMessage);
	}
}
