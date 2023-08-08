package com.donatoordep.anime_list_api.controllers.handlers;

import com.donatoordep.anime_list_api.services.exceptions.AnimeAlreadyInCartException;
import com.donatoordep.anime_list_api.services.exceptions.CustomizedException;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.services.exceptions.UserExistsInDatabaseException;
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
        return handlingException(e, HttpStatus.CONFLICT, request.getRequestURI());
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<CustomizedException> notFoundEntityException(
            NotFoundEntityException e, HttpServletRequest request) {
        return handlingException(e, HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(AnimeAlreadyInCartException.class)
    public ResponseEntity<CustomizedException> animeAlreadyInCart(
            AnimeAlreadyInCartException e, HttpServletRequest request) {
        return handlingException(e, HttpStatus.CONFLICT, request.getRequestURI());
    }

    private ResponseEntity<CustomizedException> handlingException(
            Exception e, HttpStatus status, String path){
        return ResponseEntity.status(status).body(
                new CustomizedException(e.getMessage(), status.value(), path));
    }
}
