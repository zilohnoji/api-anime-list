package com.donatoordep.anime_list_api.services;

import com.auth0.jwt.JWT;
import com.donatoordep.anime_list_api.builders.UserBuilder;
import com.donatoordep.anime_list_api.builders.dto.response.AuthenticationResponseDTOBuilder;
import com.donatoordep.anime_list_api.dto.request.AuthenticationRequestDTO;
import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AuthenticationResponseDTO;
import com.donatoordep.anime_list_api.dto.response.CartResponseDTO;
import com.donatoordep.anime_list_api.dto.response.UserResponseDTO;
import com.donatoordep.anime_list_api.entities.*;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import com.donatoordep.anime_list_api.security.TokenJWTService;
import com.donatoordep.anime_list_api.services.business.rules.user.findByName.FindByNameArgs;
import com.donatoordep.anime_list_api.services.business.rules.user.findByName.FindByNameValidation;
import com.donatoordep.anime_list_api.services.business.rules.user.register.RegisterUserArgs;
import com.donatoordep.anime_list_api.services.business.rules.user.register.RegisterUserValidation;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ConvertingType mapper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenJWTService tokenJWTService;

    @Autowired
    private List<RegisterUserValidation> userRegisterValidations;

    @Autowired
    private List<FindByNameValidation> findByNameValidations;

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findByName(String name, Pageable pageable) {
        findByNameValidations.forEach(v -> v.verification(new FindByNameArgs(repository, name, pageable)));
        return repository.findByName(name, pageable)
                .map(user -> mapper.convertUserToUserResponseDTO(user));
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO objectOfAuthentication) {

        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(
                objectOfAuthentication.getEmail(), objectOfAuthentication.getPassword()));

        String token = tokenJWTService.generateToken((User) authenticate.getPrincipal());

        return AuthenticationResponseDTOBuilder.builder()
                .login(authenticate.getName())
                .issuer(JWT.decode(token).getIssuer())
                .expireToken(JWT.decode(token).getExpiresAt())
                .token(token)
                .build();
    }

    @Transactional
    public UserResponseDTO register(UserRequestDTO dto) {

        userRegisterValidations.forEach(v -> v.verification(new RegisterUserArgs(dto, repository)));

        User user = UserBuilder.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .cart()
                .profile(dto.getProfile().getImgUrl(), dto.getProfile().getBio())
                .build();

        user.setRoles(roleService.separateRolesWithHierarchy(ConvertingType.convertStringToEnum(RoleName.class, dto.getRole())));

        return mapper.convertUserToUserResponseDTO(repository.save(user));
    }

    @Transactional(readOnly = true)
    public UserResponseDTO me(User user) {
        return mapper.convertUserToUserResponseDTO(user);
    }

    public void update(User user) {
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public CartResponseDTO myCart(User user) {
        return new CartResponseDTO((user.getCart()));
    }
}