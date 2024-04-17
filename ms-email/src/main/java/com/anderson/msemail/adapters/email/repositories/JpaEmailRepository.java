package com.anderson.msemail.adapters.email.repositories;

import com.anderson.msemail.adapters.email.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaEmailRepository extends JpaRepository<EmailEntity, UUID> {
}
