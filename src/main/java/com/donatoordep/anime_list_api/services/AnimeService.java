package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.AnimeDTO;
import com.donatoordep.anime_list_api.mappers.AnimeMapper;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<AnimeDTO> findByName(String name) {
        List<AnimeDTO> animeList = repository.findByName(name).stream().map(AnimeDTO::new).toList();

        if (animeList.isEmpty()) {
            throw new NotFoundEntityException();
        }
        return animeList;
    }

    @Transactional(readOnly = true)
    public Page<AnimeDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(AnimeDTO::new);
    }
}
