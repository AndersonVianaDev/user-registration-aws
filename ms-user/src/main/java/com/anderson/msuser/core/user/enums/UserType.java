package com.anderson.msuser.core.user.enums;

import com.anderson.msuser.shared.exceptions.InvalidDataException;

import static com.anderson.msuser.shared.constants.ExceptionConstants.INVALID_CODE;

public enum UserType {
    ADMIN(1),
    COMMON(2);

    private int code;

    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserType valueOf(int code) {
        for(UserType value : UserType.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new InvalidDataException(INVALID_CODE);
    }
}
