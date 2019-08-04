package com.itsector.backoffice.usecase.users.errors;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
    }


    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
