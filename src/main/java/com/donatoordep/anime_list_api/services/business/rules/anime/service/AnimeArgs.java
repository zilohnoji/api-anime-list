package com.donatoordep.anime_list_api.services.business.rules.anime.service;

import com.donatoordep.anime_list_api.repositories.AnimeRepository;

public record AnimeArgs(AnimeRepository repository, String name) {
}
