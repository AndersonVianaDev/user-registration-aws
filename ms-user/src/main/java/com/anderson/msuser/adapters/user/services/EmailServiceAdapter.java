package com.anderson.msuser.adapters.user.services;

import com.anderson.msuser.core.user.dtos.EmailDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.services.EmailService;
import com.anderson.msuser.producer.Producer;

public class EmailServiceAdapter implements EmailService {

    private final Producer producer;

    public EmailServiceAdapter(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void sendMessage(User user) {
        String subject = "Registration completed successfully!";
        String text = user.getName() + ", welcome ! Take advantage of our platform!";

        EmailDTO emailDTO = new EmailDTO(user.getId(), user.getEmail(), subject, text);

        producer.publishMessageEmail(emailDTO);
    }
}
