package com.anderson.msemail.adapters.email.repositories;

import com.anderson.msemail.adapters.email.entity.EmailEntity;
import com.anderson.msemail.core.email.model.Email;
import com.anderson.msemail.core.email.repository.EmailRepository;
import org.springframework.stereotype.Repository;

import static com.anderson.msemail.shared.mapper.EmailMapper.toEmailEntity;

@Repository
public class EmailRepositoryAdapter implements EmailRepository {

    private final JpaEmailRepository repository;

    public EmailRepositoryAdapter(JpaEmailRepository repository) {
        this.repository = repository;
    }


    @Override
    public void save(Email email) {
        EmailEntity emailEntity = toEmailEntity(email);

        repository.save(emailEntity);
    }
}
