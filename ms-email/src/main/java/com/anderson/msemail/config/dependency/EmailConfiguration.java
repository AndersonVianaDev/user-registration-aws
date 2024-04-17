package com.anderson.msemail.config.dependency;

import com.anderson.msemail.adapters.email.repositories.EmailRepositoryAdapter;
import com.anderson.msemail.adapters.email.services.EmailSenderAdapter;
import com.anderson.msemail.core.email.services.EmailService;
import com.anderson.msemail.core.email.services.impl.EmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Bean
    public EmailService emailService(EmailRepositoryAdapter repository, EmailSenderAdapter emailSender) {
        return new EmailServiceImpl(repository, emailSender);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("sandbox.smtp.mailtrap.io");
        mailSender.setPort(587);
        mailSender.setUsername("dc23c01d922cec");
        mailSender.setPassword("1e412baefb6416");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

}
