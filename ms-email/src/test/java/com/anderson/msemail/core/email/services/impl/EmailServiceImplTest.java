package com.anderson.msemail.core.email.services.impl;

import com.anderson.msemail.core.email.dtos.EmailDTO;
import com.anderson.msemail.core.email.enums.StatusEmail;
import com.anderson.msemail.core.email.model.Email;
import com.anderson.msemail.core.email.repository.EmailRepository;
import com.anderson.msemail.core.email.services.EmailSender;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import static com.anderson.msemail.builder.EmailBuilder.toEmailDTO;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl service;
    @Mock
    private EmailRepository repository;
    @Mock
    private EmailSender emailSender;
    @Captor
    ArgumentCaptor<Email> emailCaptor;

    @Test
    @DisplayName("Insert email successfully")
    void insert() throws Exception {
        // arrange
        EmailDTO dto = toEmailDTO();

        // action
        service.insert(dto);

        // assertions
        verify(emailSender, times(1)).sendEmail(emailCaptor.capture());
        Email email = emailCaptor.getValue();

        verify(repository, times(1)).save(email);

        assertEquals(StatusEmail.SEND, email.getStatusEmail());
    }

    @Test
    @DisplayName("Email with status email error")
    void insertStatusEmailError() throws Exception {
        // arrange
        EmailDTO dto = toEmailDTO();

        doThrow(Exception.class).when(emailSender).sendEmail(any(Email.class));

        // action
        service.insert(dto);

        //assertions
        verify(emailSender, times(1)).sendEmail(emailCaptor.capture());
        Email email = emailCaptor.getValue();
        
        verify(repository, times(1)).save(email);

        assertEquals(StatusEmail.ERROR, email.getStatusEmail());
    }
}