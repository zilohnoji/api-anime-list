package com.donatoordep.anime_list_api.services.exceptions;

public class InvalidEnumValueException extends RuntimeException {

    public InvalidEnumValueException(String value, String enumType, String listEnums) {
        super(String.format(
                "The value you entered %s to the type %s is not valid, valid values %s",
                        value, enumType, listEnums));
    }
}
