package com.itsector.backoffice.usecase.users.gateway;

import com.itsector.backoffice.domain.User;

import java.util.List;

/**
 * User Gateway
 */
public interface UsersGateway {

    /**
     *
     * @return a list with all users
     */
    List<User> getAllUsers();

    /**
     *
     * @param user
     * @return user id
     */
    Integer createUser(User user);
}
