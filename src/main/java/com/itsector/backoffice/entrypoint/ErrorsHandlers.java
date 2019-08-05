package com.itsector.backoffice.entrypoint;

import com.itsector.backoffice.usecase.users.errors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class ErrorsHandlers {

    @ExceptionHandler(UserNotFoundException.class)
    public void handleNotFound(final HttpServletResponse response, final UserNotFoundException e) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    public void handleUserAlreadyExist(final HttpServletResponse response, final UserNotFoundException e) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(), e.getMessage());
    }
}