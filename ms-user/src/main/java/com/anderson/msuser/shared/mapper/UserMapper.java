package com.anderson.msuser.shared.mapper;

import com.anderson.msuser.adapters.user.entity.UserEntity;
import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.api.validation.UserRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public static UserDTO toUserDTO(UserRequestDTO dto) {
        return new UserDTO(dto.name(), dto.email(), dto.password());
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getUserType().getCode());
    }

    public static User toUser(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getUserType());
    }

    public static Optional<User> toOptionalUser(Optional<UserEntity> userEntity) {
        return userEntity.map(entity -> new User(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword(), entity.getUserType()));
    }
}
