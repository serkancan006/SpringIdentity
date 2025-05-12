package com.example.springidentity.identity.exceptions;

public class EmailNotConfirmedException extends RuntimeException {
    public EmailNotConfirmedException(String message) {
        super(message);
    }
}
