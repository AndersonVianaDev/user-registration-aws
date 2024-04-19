package com.anderson.msuser.core.user.services.impl;

import builders.user.UserBuilder;
import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import com.anderson.msuser.core.user.services.EmailService;
import com.anderson.msuser.core.user.services.PasswordEncodeService;
import com.anderson.msuser.shared.exceptions.AccountAlreadyRegisteredException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.anderson.msuser.shared.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncodeService passwordEncode;

    @Mock
    private EmailService emailService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    @DisplayName("insert user successfully")
    void insert() {
        // arrange
        UserDTO dto = UserBuilder.toUserDTO();
        User user = UserBuilder.toUser();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.empty());
        when(passwordEncode.encode(dto.password())).thenReturn("password");
        when(repository.save(any(User.class))).thenReturn(user);

        // action
        UserResponseDTO userResponse = service.insert(dto);

        // assertions
        verify(repository, times(1)).save(userCaptor.capture());
        User userResul = userCaptor.getValue();

        verify(emailService, times(1)).sendMessage(any(User.class));

        assertEquals(userResponse.name(), userResul.getName());
        assertEquals(userResponse.email(), userResul.getEmail());
    }

    @Test
    @DisplayName("Insert with exception already registered account")
    void insertException() {
        // arrange
        UserDTO dto = UserBuilder.toUserDTO();
        User user = UserBuilder.toUser();

        when(repository.findByEmail(dto.email())).thenReturn(Optional.of(user));

        // action
        AccountAlreadyRegisteredException exception = assertThrows(AccountAlreadyRegisteredException.class, () -> service.insert(dto));

        // assertions
        assertEquals(EMAIL_ALREADY_REGISTERED, exception.getMessage());
    }

    @Test
    @DisplayName("Find all users successfully")
    void findAll() {
        // arrange
        List<User> userList = UserBuilder.toUserList();

        when(repository.findAll(0, 3)).thenReturn(userList);

        // action
        List<User> userListResul = service.findAll(0, 3);

        // assertions
        assertEquals(userList.size(), userListResul.size());
    }
}