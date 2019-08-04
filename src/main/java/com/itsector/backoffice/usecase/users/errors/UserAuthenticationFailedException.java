package com.itsector.backoffice.usecase.users.errors;

public class UserAuthenticationFailedException extends RuntimeException {

    public UserAuthenticationFailedException() {
    }

    public UserAuthenticationFailedException(String message) {
        super(message);
    }
}
