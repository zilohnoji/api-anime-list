package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.AccountStatsDTO;
import com.donatoordep.anime_list_api.dto.UserDTO;
import com.donatoordep.anime_list_api.entities.AccountStats;
import com.donatoordep.anime_list_api.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountStatsMapper {

    AccountStatsMapper INSTANCE = Mappers.getMapper(AccountStatsMapper.class);

    AccountStats toEntity(AccountStatsDTO dto);

    AccountStatsDTO toDto(AccountStats entity);
}
