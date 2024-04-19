package com.anderson.msuser.producer;

import com.anderson.msuser.core.user.dtos.EmailDTO;
import com.anderson.msuser.shared.exceptions.UnexpectedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.anderson.msuser.shared.constants.ExceptionConstants.UNEXPECTED_EXCEPTION;

@Component
public class Producer {

    private final SqsTemplate sqsTemplate;
    private final ObjectMapper mapper;

    @Value("${url-queue}")
    private String queue;

    public Producer(SqsTemplate sqsTemplate, ObjectMapper mapper) {
        this.sqsTemplate = sqsTemplate;
        this.mapper = mapper;
    }

    public void publishMessageEmail(EmailDTO dto) {
        try {
            String dtoString = mapper.writeValueAsString(dto);
            sqsTemplate.send(queue, dtoString);
        } catch(Exception e) {
            throw new UnexpectedException(UNEXPECTED_EXCEPTION);
        }
    }
}
