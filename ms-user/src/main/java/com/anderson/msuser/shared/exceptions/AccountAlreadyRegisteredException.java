package com.anderson.msuser.shared.exceptions;

public class AccountAlreadyRegisteredException extends RuntimeException {
    public AccountAlreadyRegisteredException(String message) {
        super(message);
    }
}
