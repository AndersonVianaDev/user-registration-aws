package com.anderson.msuser.core.shared;

public interface PasswordEncodeService {

    String encode(String rawPassword);

    boolean compare(String password, String rawPassword);
}
