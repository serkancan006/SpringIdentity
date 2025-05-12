package com.example.springidentity.identity.exceptions;

public class InvalidRoleAssignmentException extends RuntimeException {
    public InvalidRoleAssignmentException(String message) {
        super(message);
    }
}
