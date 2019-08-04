package com.itsector.backoffice.usecase.users.gateway;

import com.itsector.backoffice.domain.User;

import java.util.List;
import java.util.Optional;

public interface UsersGateway {

    List<User> getAllUsers();

    Integer createUser(User user);

    Integer deleteUser(Integer id);

    Integer updateUser(User id);

    Optional<User> getUserById(Integer id);

    Optional<User> getUserByUserName(String userName);

}
