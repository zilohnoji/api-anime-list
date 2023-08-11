package com.donatoordep.anime_list_api.controllers.handlers;

import com.donatoordep.anime_list_api.dto.FieldMessage;
import com.donatoordep.anime_list_api.dto.ValidationError;
import com.donatoordep.anime_list_api.services.exceptions.AnimeAlreadyInCartException;
import com.donatoordep.anime_list_api.services.exceptions.CustomizedException;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.services.exceptions.UserExistsInDatabaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomizedException> methodArgumentNotValid(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError error = new ValidationError("Invalid data!!",
                HttpStatus.UNPROCESSABLE_ENTITY.value(), request.getRequestURI());
        e.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        error.add(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage())));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    private ResponseEntity<CustomizedException> handlingException(
            Exception e, HttpStatus status, String path) {
        return ResponseEntity.status(status).body(
                new CustomizedException(e.getMessage(), status.value(), path));
    }
}
