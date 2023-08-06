package com.donatoordep.anime_list_api.services;

import com.auth0.jwt.JWT;
import com.donatoordep.anime_list_api.dto.AuthenticationDTO;
import com.donatoordep.anime_list_api.dto.TokenAuthenticationSuccessfulDTO;
import com.donatoordep.anime_list_api.dto.UserAuthenticatedDTO;
import com.donatoordep.anime_list_api.dto.UserDTO;
import com.donatoordep.anime_list_api.entities.*;
import com.donatoordep.anime_list_api.mappers.UserMapper;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.security.WebSecurityConfig;
import com.donatoordep.anime_list_api.services.exceptions.EntityNotAuthenticatedInSystemException;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.services.exceptions.UserExistsInDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findByName(String name, Pageable pageable) {
        if (repository.findByName(name, pageable).isEmpty()) {
            throw new NotFoundEntityException();
        }
        return repository.findByName(name, pageable).map(user -> mapper.toDto(user));
    }

    public TokenAuthenticationSuccessfulDTO login(AuthenticationDTO objectOfAuthentication) {
        UserDetails userObject = repository.findByEmail(objectOfAuthentication.getEmail());
        if (userObject == null || !(encoder.matches(objectOfAuthentication.getPassword(), userObject.getPassword()))) {
            throw new NotFoundEntityException();
        }
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

        User user = new User(dto.getName(), dto.getEmail(),
                webSecurityConfig.passwordEncoder().encode(dto.getPassword()), new Cart());

        user.setProfile(new ProfileUser(
                new AccountStats(), dto.getProfile().getImgUrl(), dto.getProfile().getBio()));

        user.setRoles(roleService.separateRolesWithHierarchy(dto.getRoles().stream().map(
                roleDTO -> new Role(roleDTO.getId(), roleDTO.getRoleName())).toList()));

        return mapper.toDto(repository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDTO me() {
        return mapper.toDto(authenticated());
    }

    @Transactional(readOnly = true)
    public User authenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new EntityNotAuthenticatedInSystemException();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return repository.findEmailForUserAuthenticate(userDetails.getUsername());
    }
}
