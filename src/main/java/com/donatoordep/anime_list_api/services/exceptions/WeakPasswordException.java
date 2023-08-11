package com.donatoordep.anime_list_api.services.exceptions;

public class WeakPasswordException extends RuntimeException{
    private static final String ERROR = "Weak password, minimum is 5 characters";

    public WeakPasswordException(){
        super(ERROR);
    }
}
