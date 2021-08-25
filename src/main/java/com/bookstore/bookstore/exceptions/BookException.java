package com.bookstore.bookstore.exceptions;

public class BookException extends Exception{

	private static final long serialVersionUID = 1L;
	private final int errorCode;
	private final String errorDescription;
	
	public BookException(int errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	
	
	public int getErrorCode() {
		return errorCode;
	}

	
	public String getErrorDescription() {
		return errorDescription;
	}

	
	@Override
	public String toString() {
		return "BookException [errorCode " + errorCode + ", " + errorDescription + "]";
	}
}
