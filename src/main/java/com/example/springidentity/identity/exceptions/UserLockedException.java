package com.example.springidentity.identity.exceptions;

public class UserLockedException extends RuntimeException {
    public UserLockedException(String message) {
        super(message);
    }
}
