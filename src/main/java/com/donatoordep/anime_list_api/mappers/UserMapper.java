package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.builders.dto.response.UserResponseDTOBuilder;
import com.donatoordep.anime_list_api.dto.*;
import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AccountStatsResponseDTO;
import com.donatoordep.anime_list_api.dto.response.CartResponseDTO;
import com.donatoordep.anime_list_api.dto.response.ProfileUserResponseDTO;
import com.donatoordep.anime_list_api.dto.response.UserResponseDTO;
import com.donatoordep.anime_list_api.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public UserResponseDTO toDto(User entity) {

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