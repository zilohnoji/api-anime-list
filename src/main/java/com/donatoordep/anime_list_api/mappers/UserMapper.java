package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.*;
import com.donatoordep.anime_list_api.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO dto);

    default UserDTO toDto(User entity) {

        UserDTO dto = new UserDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword());

        ProfileUserDTO profileUserDTO = new ProfileUserDTO(entity.getProfile());
        profileUserDTO.setAccountStats(new AccountStatsDTO(entity.getProfile().getAnimeStats()));

        dto.setProfile(profileUserDTO);
        dto.setCart(new CartDTO(entity.getCart()));

        entity.getRoles().forEach(role -> {
            RoleDTO roleDTO = new RoleDTO(role);
            roleDTO.setRoleName(role.getRoleName());
            dto.addRole(roleDTO);
        });
        return dto;
    }
}