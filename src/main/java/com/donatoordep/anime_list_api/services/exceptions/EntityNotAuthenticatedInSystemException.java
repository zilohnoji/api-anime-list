package com.donatoordep.anime_list_api.services.exceptions;

public class EntityNotAuthenticatedInSystemException extends RuntimeException{

    private static final String ERROR = "user not authenticate";

    public EntityNotAuthenticatedInSystemException(){
        super(ERROR);
    }
}
