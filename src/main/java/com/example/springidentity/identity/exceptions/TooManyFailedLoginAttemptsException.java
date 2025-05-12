package com.example.springidentity.identity.exceptions;

public class TooManyFailedLoginAttemptsException extends RuntimeException {
    public TooManyFailedLoginAttemptsException(String message) {
        super(message);
    }
}
