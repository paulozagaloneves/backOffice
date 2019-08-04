package com.itsector.backoffice.usecase.users;

import com.itsector.backoffice.domain.User;

public interface Login {

    User execute(String userName, String password);
}
