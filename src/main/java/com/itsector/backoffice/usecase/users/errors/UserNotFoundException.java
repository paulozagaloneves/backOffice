package com.itsector.backoffice.usecase.users.errors;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
    }


    public UserNotFoundException(final String message) {
        super(message);
    }
}
