package com.anderson.msuser.core.services;

public interface PasswordEncodeService {

    String encode(String rawPassword);

    boolean compare(String password, String rawPassword);
}
