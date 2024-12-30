package com.tasks.taskmanager.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tasks.taskmanager.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exception(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
	}

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(ResourseNotFoundException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getException(),
				request.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
	}
}
