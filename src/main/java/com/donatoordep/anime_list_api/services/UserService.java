package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.UserDTO;
import com.donatoordep.anime_list_api.mappers.UserMapper;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Transactional(readOnly = true)
    public List<UserDTO> findByName(String name) {
        return repository.findByName(name).stream().map(user -> mapper.toDto(user)).toList();
    }
}
