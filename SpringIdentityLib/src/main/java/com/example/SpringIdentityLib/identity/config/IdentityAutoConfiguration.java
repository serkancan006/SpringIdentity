package com.example.SpringIdentityLib.identity.config;

import com.example.SpringIdentityLib.identity.repositories.IdentityRoleRepo;
import com.example.SpringIdentityLib.identity.repositories.IdentityUserRepo;
import com.example.SpringIdentityLib.identity.services.RoleManagerImpl;
import com.example.SpringIdentityLib.identity.services.UserManagerImpl;
import com.example.SpringIdentityLib.identity.services.interfaces.RoleManager;
import com.example.SpringIdentityLib.identity.services.interfaces.UserManager;
import com.example.SpringIdentityLib.identity.utils.PasswordUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(IdentityConfig.class)
public class IdentityAutoConfiguration {

    private final IdentityRoleRepo identityRoleRepo;
    private final IdentityUserRepo identityUserRepo;
    private final PasswordUtil passwordUtil;
    private final IdentityConfig identityConfig;

    public IdentityAutoConfiguration(IdentityRoleRepo identityRoleRepo,
                                     IdentityUserRepo identityUserRepo,
                                     PasswordUtil passwordUtil,
                                     IdentityConfig identityConfig) {
        this.identityRoleRepo = identityRoleRepo;
        this.identityUserRepo = identityUserRepo;
        this.passwordUtil = passwordUtil;
        this.identityConfig = identityConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public UserManager<?> userManager() {
        return new UserManagerImpl<>(identityUserRepo, identityRoleRepo, passwordUtil, identityConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public RoleManager<?> roleManager() {
        return new RoleManagerImpl<>(identityRoleRepo);
    }
}
