package com.anderson.msuser.config.dependency;

import com.anderson.msuser.adapters.user.repositories.UserEntityRepository;
import com.anderson.msuser.adapters.user.services.EmailServiceAdapter;
import com.anderson.msuser.adapters.user.services.PasswordEncodeAdapter;
import com.anderson.msuser.core.user.services.UserService;
import com.anderson.msuser.core.user.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserService userService(UserEntityRepository repository, PasswordEncodeAdapter passwordEncode, EmailServiceAdapter emailService) {
        return new UserServiceImpl(repository, passwordEncode, emailService);
    }
}
