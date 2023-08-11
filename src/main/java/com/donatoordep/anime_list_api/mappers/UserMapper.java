package com.donatoordep.anime_list_api.mappers;

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

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default UserResponseDTO toDto(User entity) {

        UserResponseDTO dto = new UserResponseDTO(entity.getId(), entity.getName(), entity.getEmail());
        ProfileUserResponseDTO profileUserDTO = new ProfileUserResponseDTO(entity.getProfile());
        profileUserDTO.setAccountStats(new AccountStatsResponseDTO(entity.getProfile().getAnimeStats()));

        dto.setProfile(profileUserDTO);
        dto.setCart(new CartResponseDTO(entity.getCart()));

        entity.getRoles().forEach(role -> dto.addRole(new RoleDTO(role)));
        return dto;
    }
}