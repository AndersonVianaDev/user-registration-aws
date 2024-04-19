package com.anderson.msuser.adapters.services;

import com.anderson.msuser.core.services.PasswordEncodeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodeAdapter implements PasswordEncodeService {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncodeAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean compare(String password, String rawPassword) {
        return passwordEncoder.matches(rawPassword, password);
    }
}
