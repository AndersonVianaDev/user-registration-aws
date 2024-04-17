package com.anderson.msemail.adapters.email.services;

import com.anderson.msemail.core.email.model.Email;
import com.anderson.msemail.core.email.services.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderAdapter implements EmailSender {

    private final JavaMailSender mailSender;

    @Value("${email-from}")
    private String emailFrom;

    public EmailSenderAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    public void sendEmail(Email email) throws Exception {
        var message = new SimpleMailMessage();

        message.setFrom(emailFrom);
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());

        mailSender.send(message);
    }
}
