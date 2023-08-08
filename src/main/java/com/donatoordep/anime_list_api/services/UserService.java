package com.donatoordep.anime_list_api.services;

import com.auth0.jwt.JWT;
import com.donatoordep.anime_list_api.dto.request.AuthenticationRequestDTO;
import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AuthenticationResponseDTO;
import com.donatoordep.anime_list_api.dto.response.CartResponseDTO;
import com.donatoordep.anime_list_api.dto.response.UserResponseDTO;
import com.donatoordep.anime_list_api.entities.*;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.mappers.UserMapper;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.security.WebSecurityConfig;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.services.exceptions.UserExistsInDatabaseException;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RoleService roleService;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    private TokenJWTService tokenJWTService;

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findByName(String name, Pageable pageable) {
        if (repository.findByName(name, pageable).isEmpty()) {
            throw new NotFoundEntityException();
        }
        return repository.findByName(name, pageable).map(user -> mapper.toDto(user));
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO objectOfAuthentication) {

        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(
                objectOfAuthentication.getEmail(), objectOfAuthentication.getPassword()));

        String token = tokenJWTService.generateToken((User) authenticate.getPrincipal());

        return new AuthenticationResponseDTO(token, authenticate.getName(), JWT.decode(token).getIssuer());
    }

    @Transactional
    public UserResponseDTO register(UserRequestDTO dto) {
        if(repository.findEmailForUser(dto.getEmail()) != null){
            throw new UserExistsInDatabaseException();
        }

        User user = new User(dto.getName(), dto.getEmail(),
                webSecurityConfig.passwordEncoder().encode(dto.getPassword()), new Cart());

        user.setProfile(new ProfileUser(
                new AccountStats(), dto.getProfile().getImgUrl(), dto.getProfile().getBio()));

        user.setRoles(roleService.separateRolesWithHierarchy(ConvertingType.convertStringForEnum(RoleName.class, dto.getRole())));

        return mapper.toDto(repository.save(user));
    }

    @Transactional(readOnly = true)
    public UserResponseDTO me(User user) {
        return mapper.toDto(user);
    }

    public void update(User user) {
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public CartResponseDTO myCart(User user) {
        return new CartResponseDTO((user.getCart()));
    }
}
