package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.response.ProfileUserResponseDTO;
import com.donatoordep.anime_list_api.entities.ProfileUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileUserMapper {

    ProfileUserMapper INSTANCE = Mappers.getMapper(ProfileUserMapper.class);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "animeStats", ignore = true)
    ProfileUser toEntity(ProfileUserResponseDTO dto);

    ProfileUserResponseDTO toDto(ProfileUser entity);
}
