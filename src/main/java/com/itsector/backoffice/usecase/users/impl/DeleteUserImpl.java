package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.usecase.users.DeleteUser;
import com.itsector.backoffice.usecase.users.errors.UserNotFoundException;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserImpl implements DeleteUser {

    private final UsersGateway usersGateway;

    public DeleteUserImpl(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }


    @Override
    public String execute(Integer id) {
        if (usersGateway.deleteUser(id) > 0) {
            return "user successfully deleted";
        } else {
            throw new UserNotFoundException(String.format("user with id %d not found.", id));
        }
    }
}
