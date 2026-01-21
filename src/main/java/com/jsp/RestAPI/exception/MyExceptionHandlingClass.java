package com.jsp.RestAPI.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandlingClass {

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Map<String, String> handle(DataNotFoundException exception) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", exception.getMessage());
		return map;

	}

	@ExceptionHandler(DataExistsException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public Map<String, String> handle(DataExistsException exception) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", exception.getMessage());
		return map;

	}

}
