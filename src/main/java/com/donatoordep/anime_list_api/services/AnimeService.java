package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.AnimeDTO;
import com.donatoordep.anime_list_api.mappers.AnimeMapper;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository repository;

    @Autowired
    private AnimeMapper mapper;

    @Transactional
    public AnimeDTO createAnime(AnimeDTO dto) {
        return new AnimeDTO(repository.save(mapper.toEntity(dto)));
    }
}
