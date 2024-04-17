package com.anderson.msuser.adapters.user.repositories;

import com.anderson.msuser.adapters.user.entity.UserEntity;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import com.anderson.msuser.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserEntityRepository implements UserRepository {

    private final JpaUserRepository repository;
    private final UserMapper mapper;

    public UserEntityRepository(JpaUserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toUserEntity(user);

        userEntity = repository.save(userEntity);

        return mapper.toUser(userEntity);
    }
}
