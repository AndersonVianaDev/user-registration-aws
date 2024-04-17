package com.anderson.msemail.consumer;

import com.anderson.msemail.core.email.dtos.EmailDTO;
import com.anderson.msemail.core.email.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final ObjectMapper mapper;
    private final EmailService service;

    public Consumer(ObjectMapper mapper, EmailService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @SqsListener("test-email")
    public void listener(String data) throws JsonProcessingException {
        EmailDTO emailDTO = mapper.readValue(data, EmailDTO.class);
        service.insert(emailDTO);
    }
}
