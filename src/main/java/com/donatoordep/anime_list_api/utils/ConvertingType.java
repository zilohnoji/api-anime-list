package com.donatoordep.anime_list_api.utils;

import java.util.List;
import java.util.stream.IntStream;

public class ConvertingType {

    private ConvertingType() {
    }

    public static String listToString(List<? extends Enum<?>> enums) { // role_admin, role_client
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, enums.size())
                .forEach(i -> {
                    sb.append(enums.get(i));
                    if (i < enums.size() - 1) sb.append(", ");
                });
        return sb.toString();
    }

    public static <T extends Enum<T>> T convertStringForEnum(Class<T> enumClass, String enumOnString) {
        return Enum.valueOf(enumClass, enumOnString.toUpperCase());
    }
}
