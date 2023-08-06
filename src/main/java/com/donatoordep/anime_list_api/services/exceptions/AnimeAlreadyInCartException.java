package com.donatoordep.anime_list_api.services.exceptions;

public class AnimeAlreadyInCartException extends RuntimeException {

    private static final String ERROR = "anime already in cart";
    private Long id;

    public AnimeAlreadyInCartException(Long id) {
        super(ERROR);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
