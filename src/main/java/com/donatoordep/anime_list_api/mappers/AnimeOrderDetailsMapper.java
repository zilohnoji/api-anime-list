package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.response.AnimeOrderDetailsResponseDTO;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnimeOrderDetailsMapper {

    AnimeOrderDetailsMapper INSTANCE = Mappers.getMapper(AnimeOrderDetailsMapper.class);

    AnimeOrderDetails toEntity(AnimeOrderDetailsResponseDTO dto);

    AnimeOrderDetailsResponseDTO toDto(AnimeOrderDetails entity);
}
