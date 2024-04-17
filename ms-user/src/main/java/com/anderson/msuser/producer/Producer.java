package com.anderson.msuser.producer;

import com.anderson.msuser.core.user.dtos.EmailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final SnsTemplate snsTemplate;

    @Value("url-queue")
    private String queue;

    public Producer(SnsTemplate snsTemplate, ObjectMapper mapper) {
        this.snsTemplate = snsTemplate;
    }

    public void publishMessageEmail(EmailDTO dto) {
        try {
            snsTemplate.convertAndSend(queue, dto);
        } catch(Exception e) {
            throw new RuntimeException("");
        }
    }
}
