package com.itsector.backoffice.usecase.users;

import com.itsector.backoffice.domain.User;

public interface GetUserById {

    User execute(Integer id);

}
