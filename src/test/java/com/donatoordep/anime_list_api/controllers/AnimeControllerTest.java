package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.builders.dto.request.AnimeRequestDTOBuilder;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.enums.Status;
import com.donatoordep.anime_list_api.mapper.AnimeMapper;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.services.AnimeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    AuthenticationManager authenticateManager;

    @Autowired
    TokenJWTService tokenJWTService;

    @MockBean
    AnimeRepository repository;

    @Autowired
    AnimeMapper animeMapper;

    @MockBean
    AnimeService service;

    AnimeRequestDTO animeRequestDTO;

    AnimeResponseDTO animeResponseDTO;

    @BeforeEach
    void setup() {
        this.animeRequestDTO = AnimeRequestDTOBuilder.builder()
                .title("attack on titan")
                .description("muitos gigantes no anime de gigante")
                .imgUrl("imgUrl")
                .authorName("Pedro Donato")
                .status(Status.AIRING)
                .episodes(150)
                .build();

        this.animeResponseDTO = animeMapper.
                fromEntityToResponseDTO(animeMapper.fromAnimeRequestDTOToEntity(animeRequestDTO));

        animeResponseDTO.setId(1L);
    }

    @Test
    @DisplayName("Given AnimeResponseDTO When CreateAnime Is Called Should Return AnimeResponseDTO")
    void testGivenAnimeResponseDTO_When_CreateAnimeIsCalled_ShouldReturn_AnimeResponseDTO() throws Exception {

        when(service.createAnime(animeRequestDTO)).thenReturn(animeResponseDTO);

        ResultActions response = mockMvc.perform(
                post("/v1/anime").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token("pedro@gmail.com", "123456"))
                        .content(mapper.writeValueAsString(animeRequestDTO)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(animeResponseDTO.getTitle()))
                .andExpect(jsonPath("$.id").value(animeResponseDTO.getId()))
                .andDo(print()).andReturn();
    }

    @Test
    @DisplayName("Display name")
    void testABCD_When_XYZ_ShouldReturn_FSAD() throws Exception {

        String name = "100";

        when(service.findByName(name)).thenReturn(Collections.singletonList(animeResponseDTO));

        mockMvc.perform(get("/v1/anime?name={name}", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print()).andReturn();
    }


    protected String token(String email, String password) {

        Authentication authenticate = authenticateManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        return "Bearer " + tokenJWTService.generateToken((User) authenticate.getPrincipal());
    }
}