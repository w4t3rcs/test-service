package com.w4t3rcs.test.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, NoSuchElementException.class})
    protected ResponseEntity<?> handleValidationException(RuntimeException exception, WebRequest request) {
        return this.handleExceptionInternal(exception, "Validation has failed!", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
