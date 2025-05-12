package com.example.springidentity.identity.utils;

import java.util.List;

public interface PasswordUtil {
    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
