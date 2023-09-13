package com.donatoordep.anime_list_api.utils;

import com.donatoordep.anime_list_api.entities.Categories;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.repositories.CategoriesRepository;
import com.donatoordep.anime_list_api.services.exceptions.InvalidEnumValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ConvertingType {

    @Autowired
    private CategoriesRepository categoriesRepository;

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

    public static <T extends Enum<T>> T convertStringToEnum(Class<T> enumClass, String enumOnString) {
        if (enumClass.equals(RoleName.class) & enumOnString == null) {
            enumOnString = "role_client";
        }
        try {
            return Enum.valueOf(enumClass, enumOnString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(enumOnString, enumClass.toString(),
                    listToString(Arrays.asList(RoleName.values())));
        }
    }
}
