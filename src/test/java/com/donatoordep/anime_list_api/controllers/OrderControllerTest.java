package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.builders.AnimeBuilder;
import com.donatoordep.anime_list_api.builders.UserBuilder;
import com.donatoordep.anime_list_api.builders.dto.request.AnimeOrderDetailsRequestDTOBuilder;
import com.donatoordep.anime_list_api.builders.dto.response.AnimeOrderDetailsResponseDTOBuilder;
import com.donatoordep.anime_list_api.builders.dto.response.AnimeResponseDTOBuilder;
import com.donatoordep.anime_list_api.dto.request.AnimeOrderDetailsRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeOrderDetailsResponseDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.enums.Status;
import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.donatoordep.anime_list_api.repositories.AnimeOrderDetailsRepository;
import com.donatoordep.anime_list_api.repositories.AnimeOrderRepository;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.services.AnimeOrderDetailsService;
import com.donatoordep.anime_list_api.services.UserService;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    AuthenticationManager authenticateManager;

    @Autowired
    TokenJWTService tokenJWTService;

    @MockBean
    AnimeOrderDetailsRepository animeOrderDetailsRepository;

    @MockBean
    AnimeOrderRepository animeOrderRepository;

    @MockBean
    AnimeRepository animeRepository;

    @MockBean
    UserService userService;

    @MockBean
    AnimeOrderDetailsService service;

    User user;
    Anime anime;
    AnimeOrder animeOrder;
    AnimeOrderDetails animeOrderDetails;
    AnimeResponseDTO animeResponseDTO;
    AnimeOrderDetailsRequestDTO dtoRequest;
    AnimeOrderDetailsResponseDTO dtoResponse;

    @BeforeEach
    void setup() {
        user = UserBuilder.builder()
                .id(1L)
                .cart()
                .name("Pedro")
                .email("pedro@gmail.com")
                .password("123456")
                .profile("http://img.com", "sou o pedro")
                .build();

        dtoRequest = AnimeOrderDetailsRequestDTOBuilder.builder()
                .id(1L)
                .episode(10)
                .status("COMPLETED")
                .build();

        animeResponseDTO = AnimeResponseDTOBuilder.builder()
                .id(1L)
                .title("attack on titan")
                .description("muitos gigantes no anime de gigante")
                .imgUrl("imgUrl")
                .authorName("Pedro Donato")
                .status(Status.AIRING)
                .episodes(150)
                .build();

        dtoResponse = AnimeOrderDetailsResponseDTOBuilder.builder()
                .id(animeResponseDTO)
                .episode(10)
                .status(StatusOrder.COMPLETED)
                .build();

        anime = AnimeBuilder.builder()
                .id(1L)
                .title("attack on titan")
                .description("muitos gigantes no anime de gigante")
                .imgUrl("imgUrl")
                .authorName("Pedro Donato")
                .status(Status.AIRING)
                .episodes(150)
                .build();

        animeOrderDetails = new AnimeOrderDetails(
                anime, dtoRequest.getEpisode(), ConvertingType.convertStringToEnum(
                StatusOrder.class, dtoRequest.getStatus()));

        animeOrder = new AnimeOrder(animeOrderDetails, user.getCart());
    }

    @Test
    @DisplayName("Display name")
    void testABCD_When_XYZ_ShouldReturn_FSAD() throws Exception {
        AnimeOrderDetails saved = new AnimeOrderDetails(
                anime, dtoRequest.getEpisode(), ConvertingType.convertStringToEnum(
                StatusOrder.class, dtoRequest.getStatus()));

        animeOrderDetails.setId(1L);

        saved.setAnimeOrder(animeOrder);
        user.addAnime(animeOrder);

        when(animeRepository.findById(dtoRequest.getAnimeId())).thenReturn(Optional.ofNullable(anime));
        when(animeOrderDetailsRepository.save(animeOrderDetails)).thenReturn(saved);
        when(service.addAnimeInMyCart(dtoRequest, user)).thenReturn(dtoResponse);

        ResultActions response = mockMvc.perform(post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token("pedro@gmail.com", "123456"))
                .content(mapper.writeValueAsString(dtoRequest)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.anime").value(dtoResponse.getAnime()))
                .andExpect(jsonPath("$.episode").value(dtoResponse.getEpisode()))
                .andDo(print()).andReturn();
    }

    private String token(String email, String password) {

        Authentication authenticate = authenticateManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        return "Bearer " + tokenJWTService.generateToken((User) authenticate.getPrincipal());
    }
}