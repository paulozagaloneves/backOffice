package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.GetUserById;
import com.itsector.backoffice.usecase.users.errors.UserNotFoundException;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdImpl implements GetUserById {

    private final UsersGateway usersGateway;

    public GetUserByIdImpl(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public User execute(Integer id) {
        return usersGateway.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("user with id %d not found.", id)));
    }
}
