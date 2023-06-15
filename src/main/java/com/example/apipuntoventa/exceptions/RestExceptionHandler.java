package com.example.apipuntoventa.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;



@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({
		BadRequestException.class,
		HttpMessageNotReadableException.class,
		HttpRequestMethodNotSupportedException.class,
		TypeMismatchException.class,
		InvalidFormatException.class,
		ConstraintViolationException.class,
		MissingServletRequestParameterException.class,
		MissingRequestHeaderException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage badRequestException (Exception ex) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String,String> badRequestArgumentException(MethodArgumentNotValidException exception){
		Map<String,String> errores = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach(error ->{
			String field = ((FieldError)error).getField();
			String mensaje = error.getDefaultMessage();
			errores.put(field, mensaje);
		});
		return errores;
	}
	
	
	@ExceptionHandler({
		NotFoundException.class
	})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage notFoundException (Exception ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(),ex);
	}
	@ExceptionHandler({ConflictException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody 
	public ErrorMessage conflictException(Exception ex) {
		System.out.println("error de conflicto");
		return new ErrorMessage(HttpStatus.CONFLICT.value(), ex);
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage InternalException (Exception ex) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex);
	}
	
}
