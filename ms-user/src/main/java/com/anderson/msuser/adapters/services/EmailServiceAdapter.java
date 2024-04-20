package com.anderson.msuser.adapters.services;

import com.anderson.msuser.core.user.dtos.EmailDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.services.EmailService;
import com.anderson.msuser.producer.Producer;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EmailServiceAdapter implements EmailService {

    private final Producer producer;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public EmailServiceAdapter(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void sendMessage(User user) {
        String subject = "Registration completed successfully!";
        String text = user.getName() + ", welcome ! Take advantage of our platform!";

        EmailDTO emailDTO = new EmailDTO(user.getId(), user.getEmail(), subject, text);

        logger.info("Sending email from " + emailDTO.emailTo() + " to email microservice");
        producer.publishMessageEmail(emailDTO);
    }
}
