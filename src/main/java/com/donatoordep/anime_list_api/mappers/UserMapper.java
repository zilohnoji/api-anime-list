package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.UserDTO;
import com.donatoordep.anime_list_api.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDTO dto);

    UserDTO toDto(User entity);
}
