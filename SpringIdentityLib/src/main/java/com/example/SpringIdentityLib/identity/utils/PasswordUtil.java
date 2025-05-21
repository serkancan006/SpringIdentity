package com.example.SpringIdentityLib.identity.utils;

public interface PasswordUtil {
    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
