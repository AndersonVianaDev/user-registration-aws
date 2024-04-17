package com.anderson.msuser.config.dependency;

import com.anderson.msuser.adapters.user.repositories.UserEntityRepository;
import com.anderson.msuser.adapters.user.services.EmailServiceAdapter;
import com.anderson.msuser.adapters.user.services.PasswordEncodeAdapter;
import com.anderson.msuser.core.user.services.UserService;
import com.anderson.msuser.core.user.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {

    @Bean
    public UserService userService(UserEntityRepository repository, PasswordEncodeAdapter passwordEncodeAdapter, EmailServiceAdapter emailService) {
        return new UserServiceImpl(repository, passwordEncodeAdapter, emailService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
