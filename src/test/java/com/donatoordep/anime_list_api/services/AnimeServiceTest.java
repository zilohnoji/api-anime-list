package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.builders.AnimeBuilder;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.enums.Status;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnimeServiceTest {

    @Mock
    AnimeRepository repository;

    @InjectMocks
    AnimeService service;

    Anime anime;
    AnimeRequestDTO animeRequestDTO;
    AnimeResponseDTO animeResponseDTO;

    @BeforeEach
    void setup() {
        anime = AnimeBuilder.builder()
                .title("Attack on titan")
                .description("descrição gigante")
                .imgUrl("https://imagem.com")
                .authorName("Pedro Donato")
                .status(Status.AIRING)
                .episodes(150)
                .build();

        animeResponseDTO = new AnimeResponseDTO(anime);
        animeRequestDTO = new AnimeRequestDTO(anime);
    }

    @Test
    @DisplayName("Given AnimeResponseDTO Object When CreateAnime Is Called Should Return AnimeResponseDTO")
    void testGiven_AnimeResponseDTO_Object_When_CreateAnime_Is_Called_ShouldReturn_AnimeResponseDTO() {

        Anime saved = AnimeBuilder.builder()
                .id(1L)
                .title("Attack on titan")
                .description("descrição gigante")
                .imgUrl("https://imagem.com")
                .authorName("Pedro Donato")
                .status(Status.AIRING)
                .episodes(150)
                .build();

        when(repository.save(anime)).thenReturn(saved);

        AnimeResponseDTO output = service.createAnime(animeRequestDTO);

        Assertions.assertNotNull(output.getId(), () -> "The object not should return id null");
        Assertions.assertEquals(animeResponseDTO.getTitle(), output.getTitle(),
                () -> String.format("Expected: %s\nActual: %s",
                        animeResponseDTO.getTitle(), output.getTitle()));
    }

    @Test
    @DisplayName("Given AnimeList When FindByName Is Called Should Return AnimeList")
    void testGiven_AnimeList_When_FindByName_Is_Called_Should_Return_AnimeList() {

        List<Anime> animeList = Collections.singletonList(anime);

        when(repository.findByName(anime.getTitle())).thenReturn(animeList);
        List<AnimeResponseDTO> output = service.findByName(anime.getTitle());

        Assertions.assertNotNull(output, () -> "The object not should return null");
        Assertions.assertEquals(animeResponseDTO.getTitle(), output.get(0).getTitle(),
                () -> String.format("Expected: %s\nActual: %s",
                        animeResponseDTO.getTitle(), output.get(0).getTitle()));
    }

    @Test
    @DisplayName("Given Throw NotFoundEntityException When FindByName Is Called")
    void testGiven_Throw_NotFoundEntityException_When_FindByName_Is_Called() {

        when(repository.findByName("not-exists")).thenThrow(NotFoundEntityException.class);

        Assertions.assertThrows(NotFoundEntityException.class,
                () -> service.findByName("not-exists"),
                () -> "Should throw NotFoundEntityException");
    }

    @Test
    @DisplayName("Given Page Of AnimeResponseDTO When FindAll Is Called Should Return Page Of AnimeResponseDTO")
    void testGiven_PageOfAnimeResponseDTO_When_FindAll_Is_Called_ShouldReturn_PageOfAnimeResponseDTO() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Anime> animeList = new PageImpl<>(Collections.singletonList(anime), pageable, 1);
        Page<AnimeResponseDTO> animePage = new PageImpl<>(
                Collections.singletonList(animeResponseDTO), pageable, 1);

        when(repository.findAll(pageable)).thenReturn(animeList);

        Page<AnimeResponseDTO> output = service.findAll(pageable);

        Assertions.assertEquals(animePage, output,
                () -> String.format("Expected: %s\nActual: %s", animePage, output));
    }

    @Test
    @DisplayName("Given AnimeResponseDTO When FindById Is Called Should Return AnimeResponseDTO")
    void testGiven_AnimeResponseDTO_When_FindById_Is_Called_ShouldReturn_AnimeResponseDTO() {

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(anime));

        AnimeResponseDTO output = service.findById(1L);
        Assertions.assertNotNull(output, () -> "Not should return null");
        Assertions.assertEquals(anime.getId(), output.getId(),
                () -> String.format("Expected: %d\nActual: %d", anime.getId(), output.getId()));
    }

    @Test
    @DisplayName("Given Throw NotFoundEntityException When FindById Is Called")
    void testGiven_Throw_NotFoundEntityException_When_FindById_Is_Called() {

        when(repository.findById(1L)).thenThrow(NotFoundEntityException.class);

        Assertions.assertThrows(NotFoundEntityException.class,
                () -> service.findById(1L),
                () -> "Should throw NotFoundEntityException");
    }
}
