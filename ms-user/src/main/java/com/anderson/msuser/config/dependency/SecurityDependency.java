package com.anderson.msuser.config.dependency;

import com.anderson.msuser.adapters.user.repositories.UserEntityRepository;
import com.anderson.msuser.adapters.user.services.PasswordEncodeAdapter;
import com.anderson.msuser.adapters.user.services.TokenServiceAdapter;
import com.anderson.msuser.core.user.services.AuthService;
import com.anderson.msuser.core.user.services.TokenService;
import com.anderson.msuser.core.user.services.impl.AuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityDependency {

    @Bean
    public AuthService authService(UserEntityRepository repository, PasswordEncodeAdapter passwordEncode, TokenService tokenService) {
        return new AuthServiceImpl(repository, passwordEncode, tokenService);
    }

    @Bean
    public TokenService tokenService() {
        return new TokenServiceAdapter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
