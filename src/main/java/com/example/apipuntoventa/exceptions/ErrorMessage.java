package com.example.apipuntoventa.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {

	private Integer code;
	private String message;
	private String exception;
	public ErrorMessage(Integer code, Exception exception) {
		this.code = code;
		this.exception = exception.getClass().getName();
		this.message = exception.getMessage();
	}
	
	
}
