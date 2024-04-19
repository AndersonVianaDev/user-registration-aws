package com.anderson.msemail.core.email.enums;

import com.anderson.msemail.shared.exceptions.InvalidDataException;

import static com.anderson.msemail.shared.constants.ExceptionConstants.INVALID_CODE;

public enum StatusEmail {
    SEND(1),
    ERROR(2);

    private int code;

    StatusEmail(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusEmail valueOf(int code) {
        for(StatusEmail value : StatusEmail.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new InvalidDataException(INVALID_CODE);
    }
}
