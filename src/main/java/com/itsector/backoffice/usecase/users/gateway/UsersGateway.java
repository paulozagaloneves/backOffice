package com.itsector.backoffice.usecase.users.gateway;

import com.itsector.backoffice.domain.User;

import java.util.List;

public interface UsersGateway {

    List<User> getAllUsers();

    Integer createUser(User user);

    Integer deleteUser(Integer id);

    Integer updateUser(User id);

}
