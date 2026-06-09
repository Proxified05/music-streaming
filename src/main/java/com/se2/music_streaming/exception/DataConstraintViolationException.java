package com.se2.music_streaming.exception;

public class DataConstraintViolationException extends RuntimeException {
    public DataConstraintViolationException(String message) {
        super(message);
    }
}