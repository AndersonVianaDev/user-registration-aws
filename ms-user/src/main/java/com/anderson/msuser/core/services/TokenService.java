package com.anderson.msuser.core.services;

import com.anderson.msuser.core.user.model.User;

import java.util.UUID;

public interface TokenService {

    String generator(User user);

    UUID validateToken(String token);
}
