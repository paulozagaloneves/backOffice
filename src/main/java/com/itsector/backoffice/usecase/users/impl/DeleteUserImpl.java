package com.itsector.backoffice.usecase.users.impl;

import com.itsector.backoffice.usecase.users.DeleteUser;
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
        return usersGateway.deleteUser(id) > 0 ? "Usuario deletado com sucesso" : "Usuario não encontrado";
    }
}
