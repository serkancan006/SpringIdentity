package com.example.SpringIdentityLib.identity.exceptions;

public class UserLockedException extends RuntimeException {
    public UserLockedException(String message) {
        super(message);
    }
}
