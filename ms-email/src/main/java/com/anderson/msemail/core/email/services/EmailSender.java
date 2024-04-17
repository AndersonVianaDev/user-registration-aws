package com.anderson.msemail.core.email.services;

import com.anderson.msemail.core.email.model.Email;

public interface EmailSender {

    void sendEmail(Email email) throws Exception;
}
