package com.donatoordep.anime_list_api.services.exceptions;

public class UserExistsInDatabaseException extends RuntimeException{

    private static final String ERROR = "user has exists in database";

    public UserExistsInDatabaseException(){
        super(ERROR);
    }
}
