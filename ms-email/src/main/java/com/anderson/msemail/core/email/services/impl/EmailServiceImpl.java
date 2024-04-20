package com.anderson.msemail.core.email.services.impl;

import com.anderson.msemail.core.email.dtos.EmailDTO;
import com.anderson.msemail.core.email.enums.StatusEmail;
import com.anderson.msemail.core.email.model.Email;
import com.anderson.msemail.core.email.repository.EmailRepository;
import com.anderson.msemail.core.email.services.EmailSender;
import com.anderson.msemail.core.email.services.EmailService;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;
    private final EmailSender emailSender;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public EmailServiceImpl(EmailRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    @Override
    public void insert(EmailDTO dto) {
        Email email = new Email(dto);

        try {
            logger.fine("Try sending email to " + email.getEmailTo());
            emailSender.sendEmail(email);

            email.setStatusEmail(StatusEmail.SEND);
        } catch (Exception e) {
            logger.severe("Error sending email to " + email.getEmailTo());
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            email.setSendDateEmail(LocalDateTime.now());
            logger.info("Saving email to " + email.getEmailTo());
            repository.save(email);
        }
    }
}
