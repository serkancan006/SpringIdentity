package com.example.springidentity.identity.config;

import com.example.springidentity.identity.utils.PasswordUtil;
import com.example.springidentity.identity.utils.PasswordUtilImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class IdentityConfig {
    // kullanıcı mail onay durumu
    @Value("${com.example.springidentity.emailConfirmed:true}")
    private boolean emailConfirmed;
    // kullanıcı aktiflik
    @Value("${com.example.springidentity.isEnabled:true}")
    private boolean isEnabled;
    // Lockout (kilitleme) mekanizmasını aktif/pasif eder.
    @Value("${com.example.springidentity.lockoutEnabled:true}")
    private boolean lockoutEnabled;
    // Maksimum başarısız login denemesi sayısı.
    @Value("${com.example.springidentity.maxFailedLoginAttempts:5}")
    private int maxFailedLoginAttempts;


    @Bean
    public PasswordUtil passwordUtil(){
        return new PasswordUtilImpl();
    }
}
