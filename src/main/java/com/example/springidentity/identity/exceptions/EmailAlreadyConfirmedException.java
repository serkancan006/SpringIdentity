package com.example.springidentity.identity.exceptions;

public class EmailAlreadyConfirmedException extends RuntimeException {
    public EmailAlreadyConfirmedException(String message) {
        super(message);
    }
}
