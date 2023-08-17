package com.donatoordep.anime_list_api.services;

import com.auth0.jwt.JWT;
import com.donatoordep.anime_list_api.dto.request.AuthenticationRequestDTO;
import com.donatoordep.anime_list_api.dto.request.ProfileUserRequestDTO;
import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.dto.response.*;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.mappers.UserMapper;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.services.business.rules.user.register.RegisterUserValidation;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository repository;

    @Mock
    List<RegisterUserValidation> userRegisterValidations;

    @Mock
    PasswordEncoder encoder;

    @Mock
    RoleService roleService;

    @Mock
    UserMapper mapper;

    @InjectMocks
    UserService service;

    User user;
    UserResponseDTO userResponseDTO;

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
    }

    @Test
    @DisplayName("GivenUserRequestDTO When Register Is Called Should Return UserResponseDTO")
    void testGivenUserRequestDTO_When_RegisterIsCalled_ShouldReturn_UserResponseDTO() {

        UserRequestDTO dto = new UserRequestDTO("Pedro", "pedro@gmail.com", "123456");
        dto.setProfile(new ProfileUserRequestDTO("imagembonit", "minha bio"));

        User user = new User(dto.getName(), dto.getEmail(), encoder.encode(dto.getPassword()),
                dto.getProfile().getImgUrl(), dto.getProfile().getBio());

        when(mapper.toDto(repository.save(user))).thenReturn(userResponseDTO);

        UserResponseDTO output = service.register(dto);

        assertNotNull(output, () -> "The return can´t not");
        assertEquals(userResponseDTO, output, () -> "The object not equals, should returned same object");
        assertTrue(output.getId() > 0, () -> "The id not is valid");
    }

    @Test
    @DisplayName("Given User Object When MyCart Is Called Should Return CartResponseDTO")
    void testGivenUserObject_When_MyCartIsCalled_ShouldReturn_CartResponseDTO() {

        CartResponseDTO cartResponseDTO = new CartResponseDTO(user.getCart());
        CartResponseDTO output = service.myCart(user);

        assertEquals(output, cartResponseDTO, () -> "CartResponseDTO should return same cart");
    }

    @Test
    @DisplayName("Given Page<UserResponseDTO> When FindByName Is Called Should Return Page<UserResponseDTO>")
    void testGivenPageUserResponseDTO_When_FindByNameIsCalled_ShouldReturn_PageUserResponseDTO() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        repository.save(user);

        Page<User> userPage = new PageImpl<>(Collections.singletonList(user), pageable, 1);

        when(repository.findByName(user.getName(), pageable)).thenReturn(userPage);
        when(mapper.toDto(user)).thenReturn(userResponseDTO);

        Page<UserResponseDTO> output = service.findByName(user.getName(), pageable);

        assertNotNull(output);
        assertTrue(output.getContent().get(0).getId().equals(user.getId()),
                () -> "Id should return same id of user object");
        assertTrue(output.getContent().get(0).getName().equals(user.getName()),
                () -> "Name should return same name of user object");
    }

    @Test
    @DisplayName("Given Empty List When FindByName Is Called Should Throw NotFoundEntityException")
    void testGivenEmptyList_When_FindByNameIsCalled_ShouldThrow_NotFoundEntityException() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
        Page<User> userPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        when(repository.findByName(anyString(), eq(pageable))).thenReturn(userPage);

        assertThrows(NotFoundEntityException.class, () -> service.findByName("not-exists", pageable),
                () -> "FindByName should throw a exception NotFoundEntityException");
    }

    @Test
    @DisplayName("Display name")
    void testABCD_When_XYZ_ShouldReturn_FSAD() {

       when(service.me(user)).thenReturn(userResponseDTO);

       UserResponseDTO output = service.me(user);

       assertNotNull(output, () -> "Can´t should returned nullable");
       assertEquals(userResponseDTO, output, () -> "Should return same data");
       assertTrue(output.getId() > 0, () -> "Id can´t be null");
    }
}













