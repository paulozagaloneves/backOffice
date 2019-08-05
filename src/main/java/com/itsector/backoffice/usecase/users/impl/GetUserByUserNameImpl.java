package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.GetUserByUserName;
import com.itsector.backoffice.usecase.users.errors.UserAlreadyExistException;
import com.itsector.backoffice.usecase.users.errors.UserNotFoundException;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.springframework.stereotype.Service;

@Service
public class GetUserByUserNameImpl implements GetUserByUserName {

    private final UsersGateway usersGateway;

    public GetUserByUserNameImpl(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public User execute(String userName) {
        return usersGateway.getUserByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(String.format("user with username %d not found.", userName)));
    }
}
