package com.donatoordep.anime_list_api.exceptions;

public class UserExistsInDatabaseException extends RuntimeException{

    private static final String ERROR = "Error: user has exists";

    public UserExistsInDatabaseException(){
        super(ERROR);
    }
}
