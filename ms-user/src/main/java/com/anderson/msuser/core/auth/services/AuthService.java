package com.anderson.msuser.core.auth.services;

import com.anderson.msuser.core.auth.dtos.LoginDTO;
import com.anderson.msuser.core.auth.dtos.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginDTO dto);
}
