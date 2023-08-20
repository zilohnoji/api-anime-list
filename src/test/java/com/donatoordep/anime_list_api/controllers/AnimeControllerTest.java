package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.builders.AnimeBuilder;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.enums.Status;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.services.AnimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AnimeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AnimeService service;

    Anime anime;
    AnimeResponseDTO animeResponseDTO;
    AnimeRequestDTO animeRequestDTO;

    @BeforeEach
    void setup() {
        anime = AnimeBuilder.builder()
                .title("attack on titan")
                .description("muitos gigantes")
                .imgUrl("imgUrl")
                .authorName("Pedro Donato")
                .status(Status.AIRING)
                .episodes(150)
                .build();

        animeResponseDTO = new AnimeResponseDTO(anime);
        animeRequestDTO = new AnimeRequestDTO(anime);
    }

    @Test
    @DisplayName("Display name")
    void testABCD_When_XYZ_ShouldReturn_FSAD() throws Exception {

        when(service.createAnime(animeRequestDTO)).thenReturn(animeResponseDTO);
        mockMvc.perform(post("/v1/anime").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(animeResponseDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
