package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.builders.AnimeBuilder;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
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

    @Transactional
    public AnimeResponseDTO createAnime(AnimeRequestDTO dto) {
        Anime anime = AnimeBuilder.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .imgUrl(dto.getImgUrl())
                .authorName(dto.getAuthorName())
                .status(dto.getStatus())
                .episodes(dto.getEpisodes())
                .build();

        return new AnimeResponseDTO(repository.save(anime));
    }

    @Transactional(readOnly = true)
    public List<AnimeResponseDTO> findByName(String name) {
        List<AnimeResponseDTO> list = repository.findByName(name).stream().map(AnimeResponseDTO::new).toList();

        if (list.isEmpty()) {
            throw new NotFoundEntityException();
        }
        return list;
    }

    @Transactional(readOnly = true)
    public Page<AnimeResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(AnimeResponseDTO::new);
    }

    public AnimeResponseDTO findById(Long id) {
        return new AnimeResponseDTO(repository.findById(id).orElseThrow(NotFoundEntityException::new));
    }
}
