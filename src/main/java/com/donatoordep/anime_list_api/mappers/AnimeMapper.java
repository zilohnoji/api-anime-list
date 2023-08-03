package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.AnimeDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    Anime toEntity(AnimeDTO dto);

    AnimeDTO toDto(Anime entity);
}