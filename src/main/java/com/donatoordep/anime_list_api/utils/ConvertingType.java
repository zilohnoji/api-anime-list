package com.donatoordep.anime_list_api.utils;

import com.donatoordep.anime_list_api.builders.AnimeBuilder;
import com.donatoordep.anime_list_api.builders.UserBuilder;
import com.donatoordep.anime_list_api.builders.dto.response.UserResponseDTOBuilder;
import com.donatoordep.anime_list_api.dto.RoleDTO;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.dto.response.UserResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.services.AnimeService;
import com.donatoordep.anime_list_api.services.exceptions.InvalidEnumValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
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
        if(enumOnString == null){
            enumOnString = "role_client";
        }
        try {
            return Enum.valueOf(enumClass, enumOnString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(enumOnString, "RoleName",
                    listToString(Arrays.asList(RoleName.values())));
        }
    }

    public static Anime convertTOEntity(AnimeRequestDTO dto) {
        return AnimeBuilder.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .imgUrl(dto.getImgUrl())
                .authorName(dto.getAuthorName())
                .status(dto.getStatus())
                .episodes(dto.getEpisodes())
                .build();
    }

    public UserResponseDTO convertingUserToUserResponseDTO(User entity) {

        return UserResponseDTOBuilder.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .profile(entity.getProfile())
                .accountStats(entity.getProfile().getAnimeStats())
                .cart(entity.getCart())
                .roles(entity.getRoles().stream().map(RoleDTO::new).toList())
                .build();
    }
}
