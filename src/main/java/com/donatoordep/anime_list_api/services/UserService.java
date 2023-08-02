package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.*;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.mappers.*;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private ProfileUserMapper profileUserMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AccountStatsMapper accountStatsMapper;

    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTO>> findByName(String name) {
        List<User> usersWithEqualsName = repository.findByName(name);
        List<UserDTO> conversibleList = new ArrayList<>();

        usersWithEqualsName.forEach(entity -> {
            UserDTO i = new UserDTO();

            i.setId(entity.getId());
            i.setName(entity.getName());
            i.setEmail(entity.getEmail());
            i.setPassword(entity.getPassword());

            ProfileUserDTO profileUserDTO = profileUserMapper.toDto(entity.getProfile());
            profileUserDTO.setAnimeStats(new AccountStatsDTO(entity.getProfile().getAnimeStats()));

            i.setProfile(profileUserDTO);
            i.getProfile().setId(entity.getId());

            i.setCart(new CartDTO(entity.getCart()));

            entity.getRoles().forEach(obj -> {
                RoleDTO roleDTO = roleMapper.toDto(obj);
                roleDTO.setId(obj.getId());
                i.addRole(roleDTO);
            });

            conversibleList.add(i);
        });
        return ResponseEntity.ok().body(conversibleList);
    }
}
