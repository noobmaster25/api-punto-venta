package com.example.apipuntoventa.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -3657179714466413039L;
	public static final String DESCRIPCION = "Bad Request Exception";

	public BadRequestException(String detail) {
		super(DESCRIPCION + ". " + detail);
	}
}
