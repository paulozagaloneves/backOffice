package com.itsector.backoffice.usecase.users;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

public interface CreateUser {

    Integer execute(CreateUserRequest request);

    @Value
    @Builder
    class CreateUserRequest {
        private String name;
        private String userName;
        private String password;
        private String role;
    }
}
