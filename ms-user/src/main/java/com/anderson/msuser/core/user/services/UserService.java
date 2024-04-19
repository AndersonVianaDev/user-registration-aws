package com.anderson.msuser.core.user.services;

import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;
import com.anderson.msuser.core.user.model.User;

import java.util.List;

public interface UserService {

    UserResponseDTO insert(UserDTO dto);
    List<User> findAll(Integer page, Integer size);
}
