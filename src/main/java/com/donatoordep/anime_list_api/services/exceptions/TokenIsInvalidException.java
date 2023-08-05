package com.donatoordep.anime_list_api.services.exceptions;

public class TokenIsInvalidException extends RuntimeException{

    private static final String ERROR = "token is invalid, verify the format token";

    public TokenIsInvalidException(){
        super(ERROR);
    }
}
