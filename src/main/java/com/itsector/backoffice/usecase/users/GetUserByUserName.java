package com.itsector.backoffice.usecase.users;

import com.itsector.backoffice.domain.User;

public interface GetUserByUserName {

    User execute(String userName);
}
