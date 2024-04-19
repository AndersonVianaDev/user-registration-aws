package com.anderson.msuser.adapters.user.repositories;

import com.anderson.msuser.adapters.user.entity.UserEntity;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.anderson.msuser.shared.mapper.UserMapper.toOptionalUser;
import static com.anderson.msuser.shared.mapper.UserMapper.toUser;
import static com.anderson.msuser.shared.mapper.UserMapper.toUserEntity;
import static com.anderson.msuser.shared.mapper.UserMapper.toUserList;

@Repository
public class UserEntityRepository implements UserRepository {

    private final JpaUserRepository repository;

    public UserEntityRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = toUserEntity(user);

        userEntity = repository.save(userEntity);

        return toUser(userEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = repository.findByEmail(email);

        return toOptionalUser(userEntity);
    }

    @Override
    public List<User> findAll(Integer page, Integer size) {
        List<UserEntity> listUserEntity = repository.findAll(PageRequest.of(page, size)).getContent();

        return toUserList(listUserEntity);
    }
}