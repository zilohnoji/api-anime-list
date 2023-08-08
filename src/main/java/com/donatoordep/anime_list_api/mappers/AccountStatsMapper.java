package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.response.AccountStatsResponseDTO;
import com.donatoordep.anime_list_api.entities.AccountStats;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountStatsMapper {

    AccountStatsMapper INSTANCE = Mappers.getMapper(AccountStatsMapper.class);

    AccountStats toEntity(AccountStatsResponseDTO dto);

    AccountStatsResponseDTO toDto(AccountStats entity);
}
