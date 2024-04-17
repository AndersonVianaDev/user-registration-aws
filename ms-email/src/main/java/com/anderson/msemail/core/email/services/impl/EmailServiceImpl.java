package com.anderson.msemail.core.email.services.impl;

import com.anderson.msemail.core.email.dtos.EmailDTO;
import com.anderson.msemail.core.email.enums.StatusEmail;
import com.anderson.msemail.core.email.model.Email;
import com.anderson.msemail.core.email.repository.EmailRepository;
import com.anderson.msemail.core.email.services.EmailSender;
import com.anderson.msemail.core.email.services.EmailService;

import java.time.LocalDateTime;

public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;
    private final EmailSender emailSender;

    public EmailServiceImpl(EmailRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    @Override
    public void insert(EmailDTO dto) {
        Email email = new Email(dto);

        try {
            emailSender.sendEmail(email);

            email.setStatusEmail(StatusEmail.SEND);
        } catch (Exception e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            email.setSendDateEmail(LocalDateTime.now());
            repository.save(email);
        }
    }
}
