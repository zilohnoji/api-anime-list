package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.dto.request.ProfileUserRequestDTO;
import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AccountStatsResponseDTO;
import com.donatoordep.anime_list_api.dto.response.CartResponseDTO;
import com.donatoordep.anime_list_api.dto.response.ProfileUserResponseDTO;
import com.donatoordep.anime_list_api.dto.response.UserResponseDTO;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.mappers.UserMapper;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import com.donatoordep.anime_list_api.services.AnimeService;
import com.donatoordep.anime_list_api.services.RoleService;
import com.donatoordep.anime_list_api.services.UserService;
import com.donatoordep.anime_list_api.services.business.rules.user.register.RegisterUserValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService service;

    private User user;
    private UserResponseDTO userResponseDTO;
    private UserRequestDTO userRequestDTO;

    @BeforeEach
    void setup() {
        user = new User(
                "Pedro",
                "pedro@gmail.com",
                "123456",
                "http://img.com",
                "Sou o Pedro");
        user.setId(1L);

        userResponseDTO = new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
        ProfileUserResponseDTO profileUserDTO = new ProfileUserResponseDTO(user.getProfile());
        profileUserDTO.setAccountStats(new AccountStatsResponseDTO(user.getProfile().getAnimeStats()));

        userResponseDTO.setProfile(profileUserDTO);
        userResponseDTO.setCart(new CartResponseDTO(user.getCart()));

        userRequestDTO = new UserRequestDTO(user.getName(), user.getEmail(), user.getPassword());
        userRequestDTO.setProfile(new ProfileUserRequestDTO(user.getProfile().getImgUrl(), user.getProfile().getBio()));
    }

    @Test
    @DisplayName("Display name")
    void testABCD_When_XYZ_ShouldReturn_FSAD() throws Exception {
        UserResponseDTO expectedResponse = userResponseDTO; // O UserResponseDTO esperado

        when(service.register(any(UserRequestDTO.class)))
                .thenReturn(expectedResponse); // Configuração do mock para retornar o UserResponseDTO

        ResultActions response = mockMvc.perform(post("/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userRequestDTO))); // Use userRequestDTO aqui

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(expectedResponse.getEmail())));
    }
}
