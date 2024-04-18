package com.anderson.msuser.core.user.services.impl;

import com.anderson.msuser.core.user.dtos.LoginDTO;
import com.anderson.msuser.core.user.dtos.LoginResponseDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import com.anderson.msuser.core.user.services.AuthService;
import com.anderson.msuser.core.user.services.PasswordEncodeService;
import com.anderson.msuser.core.user.services.TokenService;

import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncodeService passwordEncode;
    private final TokenService tokenService;

    public AuthServiceImpl(UserRepository repository, PasswordEncodeService passwordEncode, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncode = passwordEncode;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponseDTO login(LoginDTO dto) {
        Optional<User> user = repository.findByEmail(dto.email());

        if(user.isEmpty() || !passwordEncode.compare(user.get().getPassword(), dto.password())) {
            throw new RuntimeException("Password or email is wrong");
        }

        return new LoginResponseDTO(tokenService.generator(user.get()));
    }
}
