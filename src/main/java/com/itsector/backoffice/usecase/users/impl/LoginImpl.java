package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.Login;
import com.itsector.backoffice.usecase.users.errors.UserAuthenticationFailedException;
import com.itsector.backoffice.usecase.users.errors.UserNotFoundException;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements Login{

    private UsersGateway usersGateway;

    public LoginImpl(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public UserDetails execute(String userName, String password) {
        final String errorMessage = "UserName or password invalid";
        User user = usersGateway.getUserByUserName(userName)
                .orElseThrow(() -> new UserAuthenticationFailedException(errorMessage));
        if (user.getPassword() == password){
            return UserDetailsService().loadUserByUsername();
        }
        throw new UserAuthenticationFailedException(errorMessage);
    }
}
