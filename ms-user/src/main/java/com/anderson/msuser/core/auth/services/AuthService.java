package com.anderson.msuser.core.user.services;

import com.anderson.msuser.core.user.dtos.LoginDTO;
import com.anderson.msuser.core.user.dtos.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginDTO dto);
}
