package com.anderson.msuser.core.user.services.impl;

import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;
import com.anderson.msuser.core.user.enums.UserType;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import com.anderson.msuser.core.services.EmailService;
import com.anderson.msuser.core.services.PasswordEncodeService;
import com.anderson.msuser.core.user.services.UserService;
import com.anderson.msuser.shared.exceptions.AccountAlreadyRegisteredException;

import java.util.List;
import java.util.logging.Logger;

import static com.anderson.msuser.shared.constants.ExceptionConstants.EMAIL_ALREADY_REGISTERED;

public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    private final PasswordEncodeService passwordEncodeService;

    private final EmailService emailService;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public UserServiceImpl(UserRepository repository, PasswordEncodeService passwordEncodeService, EmailService emailService) {
        this.repository = repository;
        this.passwordEncodeService = passwordEncodeService;
        this.emailService = emailService;
    }

    @Override
    public UserResponseDTO insert(UserDTO dto) {
        if(repository.findByEmail(dto.email()).isPresent()) throw new AccountAlreadyRegisteredException(EMAIL_ALREADY_REGISTERED);

        User user = new User(dto);

        String password = passwordEncodeService.encode(dto.password());
        user.setPassword(password);
        user.setUserType(UserType.COMMON);

        logger.info("saving user with email: " + user.getEmail());
        user = repository.save(user);

        // send confirmation email
        emailService.sendMessage(user);

        logger.info("email user "+ user.getEmail() +" is saved ");
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
    @Override
    public List<User> findAll(Integer page, Integer size) {
        return repository.findAll(page, size);
    }
}
