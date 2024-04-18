package com.anderson.msuser.core.user.services.impl;

import builders.auth.AuthBuilder;
import builders.user.UserBuilder;
import com.anderson.msuser.core.user.dtos.LoginDTO;
import com.anderson.msuser.core.user.dtos.LoginResponseDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import com.anderson.msuser.core.user.services.PasswordEncodeService;
import com.anderson.msuser.core.user.services.TokenService;
import com.anderson.msuser.shared.exceptions.InvalidDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import static com.anderson.msuser.shared.exceptions.Constants.PASSWORD_OR_EMAIL_IS_WRONG;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncodeService passwordEncode;

    @Mock
    private TokenService tokenService;

    @Test
    @DisplayName("login successfully")
    void login() {
        // arrange
        LoginDTO dto = AuthBuilder.toLoginDTO();
        User user = UserBuilder.toUser();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));
        when(passwordEncode.compare(user.getPassword(), dto.password())).thenReturn(true);
        when(tokenService.generator(user)).thenReturn("token");

        // action
        LoginResponseDTO loginResult = service.login(dto);

        // assertions
        assertEquals("token", loginResult.token());
    }

    @Test
    @DisplayName("login with exception invalid data")
    void loginException() {
        // arrange
        LoginDTO dto = AuthBuilder.toLoginDTO();
        User user = UserBuilder.toUser();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));
        when(passwordEncode.compare(user.getPassword(), dto.password())).thenReturn(false);

        // action
        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> service.login(dto));

        // assertions
        assertEquals(PASSWORD_OR_EMAIL_IS_WRONG, exception.getMessage());
    }
}