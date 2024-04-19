package com.anderson.msuser.core.user.services;

import com.anderson.msuser.core.user.model.User;

public interface EmailService {

    void sendMessage(User user);
}
