package com.anderson.msemail.core.email.repository;

import com.anderson.msemail.core.email.model.Email;

public interface EmailRepository {

    void save(Email email);
}
