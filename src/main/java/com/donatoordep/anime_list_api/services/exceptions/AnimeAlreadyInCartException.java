package com.donatoordep.anime_list_api.services.exceptions;

public class AnimeAlreadyInCartException extends RuntimeException {

    private static final String ERROR = "anime already in cart";

    public AnimeAlreadyInCartException(Long id) {
        super(String.format("%s: %d", ERROR, id));
    }
}
