package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.CreateUser;
import com.itsector.backoffice.usecase.users.errors.UserAlreadyExistException;
import com.itsector.backoffice.usecase.users.errors.UserNotFoundException;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateUserImpl implements CreateUser {

    private final UsersGateway usersGateway;

    public CreateUserImpl(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public Integer execute(CreateUserRequest request) {
        createUserDomain(request);
        return usersGateway.createUser(createUserDomain(request));
    }

    private User createUserDomain(CreateUserRequest request) {

        usersGateway.getUserByUserName(request.getUserName())
                .ifPresent(user -> {throw new UserAlreadyExistException("User name already exist");});

        return User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .userName(request.getUserName())
                .role(request.getRole()).build();

    }
}
