package com.anderson.msuser.shared.mapper;

import com.anderson.msuser.api.validation.LoginRequestDTO;
import com.anderson.msuser.core.user.dtos.LoginDTO;

public class AuthMapper {

    public static LoginDTO toLoginDTO(LoginRequestDTO dto) {
        return new LoginDTO(dto.email(), dto.password());
    }
}
