package com.donatoordep.anime_list_api.services.exceptions;

public class InvalidEnumValueException extends RuntimeException {

    public InvalidEnumValueException(String value, String enumType, String listEnums) {
    super("The value you entered " + value + " to the type " + enumType + " is not valid, valid values: " +
            listEnums);

    }

}
