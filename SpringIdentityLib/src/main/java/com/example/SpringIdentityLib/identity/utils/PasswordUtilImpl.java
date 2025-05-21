package com.example.SpringIdentityLib.identity.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtilImpl implements PasswordUtil {
    @Override
    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }


}
