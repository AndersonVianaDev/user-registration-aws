package com.anderson.msemail.shared.mapper;

import com.anderson.msemail.adapters.email.entity.EmailEntity;
import com.anderson.msemail.core.email.model.Email;

public class EmailMapper {

    public static EmailEntity toEmailEntity(Email email) {
        return new EmailEntity(email.getId(), email.getIdUser(), email.getEmailTo(), email.getSubject(), email.getText(), email.getSendDateEmail(), email.getStatusEmail().getCode());
    }
}
