package com.anderson.msuser.adapters.user.repositories;

import com.anderson.msuser.adapters.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends PagingAndSortingRepository<UserEntity, UUID> {
    UserEntity save(UserEntity user);
    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByEmail(String email);
}
