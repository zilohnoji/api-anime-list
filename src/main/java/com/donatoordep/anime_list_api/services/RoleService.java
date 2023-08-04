package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundEntityException("Not found exception"));
    }
}
