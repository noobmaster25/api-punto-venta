package com.example.apipuntoventa.exceptions;

public class ConflictException extends RuntimeException{
	private static final long serialVersionUID = -8781772106709078916L;

	public static final String DESCRIPTION = "Conflict Exception";

	public ConflictException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}


}
