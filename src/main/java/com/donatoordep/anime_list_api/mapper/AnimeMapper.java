package com.donatoordep.anime_list_api.mapper;

import com.donatoordep.anime_list_api.builders.AnimeBuilder;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.request.CategoriesRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.Categories;
import com.donatoordep.anime_list_api.repositories.CategoriesRepository;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimeMapper {

    public Anime fromAnimeRequestDTOToEntity(AnimeRequestDTO animeDTO, CategoriesRepository repository) {
        return AnimeBuilder.builder()
                .title(animeDTO.getTitle())
                .imgUrl(animeDTO.getImgUrl())
                .authorName(animeDTO.getAuthorName())
                .description(animeDTO.getDescription())
                .episodes(animeDTO.getEpisodes())
                .categories(repository, animeDTO.getCategories())
                .status(animeDTO.getStatus())
                .build();
    }

    public AnimeResponseDTO fromEntityToResponseDTO(Anime anime) {
        return new AnimeResponseDTO(anime);
    }

    public List<AnimeResponseDTO> fromListEntityToListDTO(List<Anime> animes) {
        return animes.stream().map(this::fromEntityToResponseDTO).toList();
    }

    public Page<AnimeResponseDTO> fromPageEntityToPageDTO(Page<Anime> animes) {
        return animes.map(AnimeResponseDTO::new);
    }
}