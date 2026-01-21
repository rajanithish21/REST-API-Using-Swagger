package com.jsp.RestAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataExistsException extends RuntimeException {
	private String message = "Data Exists";

}
