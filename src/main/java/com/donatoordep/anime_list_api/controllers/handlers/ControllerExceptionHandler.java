package com.donatoordep.anime_list_api.controllers.handlers;

import com.donatoordep.anime_list_api.services.exceptions.CustomizedException;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.services.exceptions.UserExistsInDatabaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserExistsInDatabaseException.class)
    public ResponseEntity<CustomizedException> userExistsInDatabase(
            UserExistsInDatabaseException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new CustomizedException(e.getMessage(), HttpStatus.CONFLICT.value(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<CustomizedException> internalAuthenticationService(
            NotFoundEntityException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new CustomizedException(e.getMessage(), HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI()));
    }
}
