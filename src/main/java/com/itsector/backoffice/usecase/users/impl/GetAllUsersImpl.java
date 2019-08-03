package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.GetAllUsers;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersImpl implements GetAllUsers {

    private final UsersGateway usersGateway;

    public GetAllUsersImpl(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public List<User> execute() {
        return usersGateway.getAllUsers();
    }
}
