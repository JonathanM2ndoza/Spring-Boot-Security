package com.jmendoza.springboot.security.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger loggerExceptionHandler = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(com.jmendoza.springboot.security.exception.ResourceNotFoundException.class)
    public ResponseEntity resourceNotFoundException(com.jmendoza.springboot.security.exception.ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        loggerExceptionHandler.error(ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({GlobalException.class})
    public ResponseEntity globalExceptionHandler(GlobalException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        loggerExceptionHandler.error(ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
