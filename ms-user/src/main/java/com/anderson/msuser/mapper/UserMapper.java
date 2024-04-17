package com.anderson.msuser.mapper;

import com.anderson.msuser.adapters.user.entity.UserEntity;
import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.api.validation.UserRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(UserRequestDTO dto) {
        return new UserDTO(dto.name(), dto.email(), dto.password());
    }

    public UserEntity toUserEntity(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getUserType());
    }

    public User toUser(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getUserType());
    }
}
