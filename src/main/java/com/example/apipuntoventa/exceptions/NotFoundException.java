package com.example.apipuntoventa.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1020361444189537092L;
	
	public static final String DESCRIPCION = "Not Found Exception";
	
	public NotFoundException(String detail) {
		super(DESCRIPCION+". "+detail);
	}

}
