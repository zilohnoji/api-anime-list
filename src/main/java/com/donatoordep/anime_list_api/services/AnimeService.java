package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.mapper.AnimeMapper;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.services.business.rules.anime.create.CreateAnimeValidation;
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
    private AnimeMapper animeMapper;

    @Autowired
    private AnimeRepository repository;

    @Transactional
    public AnimeResponseDTO createAnime(AnimeRequestDTO dto) {
        return animeMapper.fromEntityToResponseDTO(
                repository.save(animeMapper.fromAnimeRequestDTOToEntity(dto))
        );
    }

    @Transactional(readOnly = true)
    public List<AnimeResponseDTO> findByName(String name) {
        return animeMapper.fromListEntityToListDTO(repository.findByName(name));
    }

    @Transactional(readOnly = true)
    public Page<AnimeResponseDTO> findAll(Pageable pageable) {
        return animeMapper.fromPageEntityToPageDTO(repository.findAll(pageable));
    }

    public AnimeResponseDTO findById(Long id) {
        return new AnimeResponseDTO(repository.findById(id).orElseThrow(NotFoundEntityException::new));
    }
}