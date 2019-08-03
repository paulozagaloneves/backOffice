package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.usecase.users.UpdateUser;
import com.itsector.backoffice.usecase.users.gateway.UsersGateway;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserImpl implements UpdateUser {

    private final UsersGateway usersGateway;

    public UpdateUserImpl(UsersGateway usersGateway) {
        this.usersGateway = usersGateway;
    }

    @Override
    public String execute(UpdateUserRequest request) {
        createUserDomain(request);
        return usersGateway.updateUser(createUserDomain(request)) > 0 ? "Usuario atualizado com sucesso" : "Usuario não encontrado";
    }

    private User createUserDomain(UpdateUserRequest request) {
        return User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .id(request.getId())
                .build();

    }
}
