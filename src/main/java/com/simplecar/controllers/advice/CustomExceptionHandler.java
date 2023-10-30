package com.simplecar.controllers.advice;

import java.lang.reflect.InvocationTargetException;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.simplecar.models.dto.CustomExceptionDTO;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<CustomExceptionDTO> handlePropertyValueException(PropertyValueException ex) {
		CustomExceptionDTO exception = new CustomExceptionDTO();
		exception.setStatus(HttpStatus.BAD_REQUEST);
		exception.setMessage("Error in " + ex.getPropertyName() + ": " + ex.getMessage() );
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<CustomExceptionDTO> handleInvocationTarget(DataIntegrityViolationException ex) {
		CustomExceptionDTO exception = new CustomExceptionDTO();
		exception.setStatus(HttpStatus.BAD_REQUEST);
		exception.setMessage(ex.getMostSpecificCause().getMessage());
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
	}

}
