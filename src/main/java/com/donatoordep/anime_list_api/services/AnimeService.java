package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.request.CategoriesRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.Categories;
import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.enums.Category;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.mapper.AnimeMapper;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.repositories.CategoriesRepository;
import com.donatoordep.anime_list_api.services.business.rules.anime.create.CreateAnimeArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.create.CreateAnimeValidation;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class AnimeService {

    @Autowired
    private AnimeMapper animeMapper;

    @Autowired
    private AnimeRepository repository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private List<CreateAnimeValidation> animeValidations;

    @Transactional
    public AnimeResponseDTO createAnime(AnimeRequestDTO dto) {
        animeValidations.forEach(v -> v.validation(new CreateAnimeArgs(dto, repository)));
        return animeMapper.fromEntityToResponseDTO(
                repository.save(animeMapper.fromAnimeRequestDTOToEntity(dto, categoriesRepository))
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