package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.AuthenticationDTO;
import com.donatoordep.anime_list_api.dto.TokenAuthenticationSuccessfulDTO;
import com.donatoordep.anime_list_api.dto.UserDTO;
import com.donatoordep.anime_list_api.entities.*;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.exceptions.UserExistsInDatabaseException;
import com.donatoordep.anime_list_api.mappers.UserMapper;
import com.donatoordep.anime_list_api.repositories.RoleRepository;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findByName(String name) {
        return repository.findByName(name).stream().map(user -> mapper.toDto(user)).toList();
    }

    @Transactional(readOnly = true)
    public TokenAuthenticationSuccessfulDTO login(AuthenticationDTO objectOfAuthentication) {
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(
                objectOfAuthentication.getEmail(), objectOfAuthentication.getPassword()));

        return new TokenAuthenticationSuccessfulDTO();
    }

    @Transactional
    public UserDTO register(UserDTO dto) {
        if (repository.findByLoginOfUser(dto.getEmail()) != null) {
            throw new UserExistsInDatabaseException("User exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setProfile(new ProfileUser(new AccountStats(), dto.getProfile().getImgUrl(),
                dto.getProfile().getBio()));
        user.setCart(new Cart());
        if (dto.getRoles().isEmpty()) {
            user.addRole(roleRepository.findById(2L).orElseThrow());
        } else {
            dto.getRoles().forEach(roleDTO -> user.addRole(new Role(roleDTO.getId(), roleDTO.getRoleName())));
        }
        repository.save(user);
        return mapper.toDto(user);
    }
}
