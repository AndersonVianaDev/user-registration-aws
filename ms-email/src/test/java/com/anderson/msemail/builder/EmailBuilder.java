package com.anderson.msemail.builder;

import com.anderson.msemail.core.email.dtos.EmailDTO;

import java.util.UUID;

public class EmailBuilder {

    public static EmailDTO toEmailDTO() {
        return new EmailDTO(UUID.randomUUID(), "name@gmail.com", "Subject", "text");
    }
}
