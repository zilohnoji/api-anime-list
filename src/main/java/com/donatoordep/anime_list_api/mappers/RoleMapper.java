package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.ProfileUserDTO;
import com.donatoordep.anime_list_api.dto.RoleDTO;
import com.donatoordep.anime_list_api.entities.ProfileUser;
import com.donatoordep.anime_list_api.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toEntity(RoleDTO dto);

    RoleDTO toDto(Role entity);
}
