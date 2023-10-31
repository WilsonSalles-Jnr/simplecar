package com.simplecar.controllers.advice;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.simplecar.models.dto.CustomExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<CustomExceptionDTO> handlePropertyValueException(PropertyValueException ex) {
	    return setterExceptions(HttpStatus.BAD_REQUEST, "Error in " + ex.getPropertyName() + ": " + ex.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<CustomExceptionDTO> handleInvocationTarget(DataIntegrityViolationException ex) {
	    return setterExceptions(HttpStatus.BAD_REQUEST, ex.getMostSpecificCause().getMessage());
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustomExceptionDTO> handleInvocationTarget(EntityNotFoundException ex) {
	    return setterExceptions(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	private ResponseEntity<CustomExceptionDTO> setterExceptions(HttpStatus status, String message) {
		CustomExceptionDTO exception = new CustomExceptionDTO();
		exception.setStatus(status);
		exception.setMessage(message);
	    return ResponseEntity.status(status).body(exception);
	}

}
