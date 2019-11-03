package com.wheeludrive2.exception;

public class WheelUDriveException extends Exception{

	/**
	 * 
	 */
	private static final String START_ERROR = "Error captured by wheeludrive: ";
	private static final long serialVersionUID = 1L;
	
	public WheelUDriveException(String exception) {

		super(START_ERROR + exception);
	}

	public WheelUDriveException(String exception, Throwable cause) {
		super(START_ERROR + exception, cause);
	}

}
