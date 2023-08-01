package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.AccountStatsDTO;
import com.donatoordep.anime_list_api.dto.CartDTO;
import com.donatoordep.anime_list_api.entities.AccountStats;
import com.donatoordep.anime_list_api.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toEntity(CartDTO dto);

    CartDTO toDto(Cart entity);
}
