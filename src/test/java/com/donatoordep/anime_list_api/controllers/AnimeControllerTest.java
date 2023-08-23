package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.builders.dto.request.AnimeRequestDTOBuilder;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.enums.Status;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.services.AnimeService;
import com.donatoordep.anime_list_api.utils.ConvertingType;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    AnimeService service;

    AnimeRequestDTO animeRequestDTO;

    AnimeResponseDTO animeResponseDTO;

    @BeforeEach
    void setup() {

        animeRequestDTO = AnimeRequestDTOBuilder.builder()
                .title("attack on titan")
                .description("muitos gigantes no anime de gigante")
                .imgUrl("imgUrl")
                .authorName("Pedro Donato")
                .status(Status.AIRING)
                .episodes(150)
                .build();

        animeResponseDTO = new AnimeResponseDTO(ConvertingType.convertTOEntity(animeRequestDTO));

    }

    @Test
    @DisplayName("Display name")
    void testABCD_When_XYZ_ShouldReturn_FSAD() throws Exception {

        when(service.createAnime(animeRequestDTO)).thenReturn(animeResponseDTO);

        mockMvc.perform(post("/v1/anime").contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token())
                        .content(mapper.writeValueAsString(animeRequestDTO)))
                .andExpect(status().isCreated())
                .andDo(print()).andReturn();
    }

    private String token() {

        Authentication authenticate = authenticateManager.authenticate(
                new UsernamePasswordAuthenticationToken("pedro@gmail.com", 123456));

        return "Bearer " + tokenJWTService.generateToken((User) authenticate.getPrincipal());
    }
}
