package com.anderson.msuser.core.user.services;

import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;

public interface UserService {

    UserResponseDTO insert(UserDTO dto);
}
