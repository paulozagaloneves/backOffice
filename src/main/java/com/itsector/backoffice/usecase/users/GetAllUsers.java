package com.itsector.backoffice.usecase.users;

import com.itsector.backoffice.domain.User;

import java.util.List;

public interface GetAllUsers {

    List<User> execute();
}
