package com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart;

import com.donatoordep.anime_list_api.dto.request.AnimeOrderDetailsRequestDTO;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;

public record AddAnimeInMyCartArgs(AnimeOrderDetailsRequestDTO dto, AnimeRepository repository, User user) {
}
