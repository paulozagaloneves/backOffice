package com.itsector.backoffice.usecase.users;

import lombok.Builder;
import lombok.Value;

public interface UpdateUser {

    String execute(UpdateUserRequest request);

    @Value
    @Builder
    class UpdateUserRequest {
        private Integer id;
        private String name;
        private String password;
    }
}
