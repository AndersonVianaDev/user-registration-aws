package com.anderson.msuser.api.controller;

import com.anderson.msuser.shared.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<StandardException> unexpected(UnexpectedException e, HttpServletRequest request) {
        StandardException exception = new StandardException(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.internalServerError().body(exception);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardException> invalidData(InvalidDataException e, HttpServletRequest request) {
        StandardException exception = new StandardException(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(exception);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFound(NotFoundException e, HttpServletRequest request) {
        StandardException exception = new StandardException(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(AccountAlreadyRegisteredException.class)
    public ResponseEntity<StandardException> alreadyRegistered(AccountAlreadyRegisteredException e, HttpServletRequest request) {
        StandardException exception = new StandardException(LocalDateTime.now(), HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardException> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        StandardException exception = new StandardException(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message, request.getRequestURI());
        return ResponseEntity.badRequest().body(exception);
    }
}
