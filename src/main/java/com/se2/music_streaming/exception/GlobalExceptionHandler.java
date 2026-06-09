package com.se2.music_streaming.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(DataNotFoundException.class)
        public ResponseEntity<ErrorMessage> handleDataNotFound(DataNotFoundException ex, HttpServletRequest request) {
                ErrorMessage error = new ErrorMessage(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI());
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(DataAlreadyExistsException.class)
        public ResponseEntity<ErrorMessage> handleDataAlreadyExists(DataAlreadyExistsException ex,
                        HttpServletRequest request) {
                ErrorMessage error = new ErrorMessage(
                                LocalDateTime.now(),
                                HttpStatus.CONFLICT.value(),
                                HttpStatus.CONFLICT.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI());
                return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ErrorMessage> handleGeneralRuntimeException(RuntimeException ex,
                        HttpServletRequest request) {
                ErrorMessage error = new ErrorMessage(
                                LocalDateTime.now(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                "Some thing went wrong. Try again!",
                                request.getRequestURI());
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(DataConstraintViolationException.class)
        public ResponseEntity<ErrorMessage> handleDataConstraintViolation(DataConstraintViolationException ex,
                        HttpServletRequest request) {
                ErrorMessage error = new ErrorMessage(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                ex.getMessage(),
                                request.getRequestURI());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
}