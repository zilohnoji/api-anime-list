package com.donatoordep.anime_list_api.controllers.handlers;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.donatoordep.anime_list_api.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomizedException> notFoundEntityException(
            NotFoundEntityException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new CustomizedException(e.getMessage(), HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(EntityNotAuthenticatedInSystemException.class)
    public ResponseEntity<CustomizedException> entityNotAuthenticatedInSystem(
            EntityNotAuthenticatedInSystemException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new CustomizedException(e.getMessage(), HttpStatus.UNAUTHORIZED.value(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(AnimeAlreadyInCartException.class)
    public ResponseEntity<CustomizedAnimeAlreadyInCartException> animeAlreadyInCart(
            AnimeAlreadyInCartException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          new CustomizedAnimeAlreadyInCartException(e.getMessage(), HttpStatus.BAD_REQUEST.value(),
                  request.getRequestURI(), e.getId()));
    }
}
