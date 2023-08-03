package com.donatoordep.anime_list_api.exceptions;

public class UserExistsInDatabaseException extends RuntimeException{

    public UserExistsInDatabaseException(String error){
        super(error);
    }
}
