package com.example.SpringIdentityLib.identity.exceptions;

public class TooManyFailedLoginAttemptsException extends RuntimeException {
    public TooManyFailedLoginAttemptsException(String message) {
        super(message);
    }
}
