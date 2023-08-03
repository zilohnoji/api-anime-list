package com.donatoordep.anime_list_api.exceptions;

public class NotFoundEntityException extends RuntimeException{

    public NotFoundEntityException(String error){
        super(error);
    }
}
