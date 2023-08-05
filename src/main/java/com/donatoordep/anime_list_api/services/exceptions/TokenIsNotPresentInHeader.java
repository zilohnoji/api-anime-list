package com.donatoordep.anime_list_api.services.exceptions;

public class TokenIsNotPresentInHeader extends RuntimeException{

    private static final String ERROR = "token is not present in header";

    public TokenIsNotPresentInHeader(){
        super(ERROR);
    }
}
