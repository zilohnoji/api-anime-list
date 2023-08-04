package com.donatoordep.anime_list_api.services;

import com.auth0.jwt.JWT;
import com.donatoordep.anime_list_api.dto.AuthenticationDTO;
import com.donatoordep.anime_list_api.dto.TokenAuthenticationSuccessfulDTO;
import com.donatoordep.anime_list_api.dto.UserDTO;
import com.donatoordep.anime_list_api.entities.*;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.exceptions.UserExistsInDatabaseException;
import com.donatoordep.anime_list_api.mappers.UserMapper;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<UserDTO> findByName(String name) {
        return repository.findByName(name).stream().map(user -> mapper.toDto(user)).toList();
    }

    public TokenAuthenticationSuccessfulDTO login(AuthenticationDTO objectOfAuthentication) {
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(
                objectOfAuthentication.getEmail(), objectOfAuthentication.getPassword()));

        String token = tokenJWTService.generateToken((User) authenticate.getPrincipal());

        return new TokenAuthenticationSuccessfulDTO(token, authenticate.getName(), JWT.decode(token).getIssuer());
    }

    @Transactional
    public UserDTO register(UserDTO dto) {
        if (repository.findByEmail(dto.getEmail()) != null) {
            throw new UserExistsInDatabaseException();
        }

        User user = new User(dto.getName(),
                webSecurityConfig.passwordEncoder().encode(dto.getPassword()), dto.getEmail(), new Cart());

        user.setProfile(new ProfileUser(
                new AccountStats(), dto.getProfile().getImgUrl(), dto.getProfile().getBio()));

        if (dto.getRoles().isEmpty()) {
            user.addRole(roleService.findById(2L));
        } else if (dto.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN))) {
            user.addRole(roleService.findById(2L)); // Client
            user.addRole(roleService.findById(1L)); // Admin
            user.addRole(roleService.findById(3L)); // Moderator
        } else if (dto.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_MODERATOR))) {
            user.addRole(roleService.findById(3L)); // Moderator
            user.addRole(roleService.findById(2L)); // Client
        } else {
            dto.getRoles().forEach(roleDTO -> user.addRole(new Role(roleDTO.getId(), roleDTO.getRoleName())));
        }

        return mapper.toDto(repository.save(user));
    }
}
