package com.donatoordep.anime_list_api.services.business.rules.anime.create;

import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;

public record CreateAnimeArgs(AnimeRequestDTO dto, AnimeRepository repository) {
}
