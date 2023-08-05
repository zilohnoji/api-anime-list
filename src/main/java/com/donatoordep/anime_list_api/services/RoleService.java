package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public List<Role> separateRolesWithHierarchy(List<Role> roles) {
        List<Role> listCreated = new ArrayList<>();
        if (roles.isEmpty()) {
            listCreated.add(this.findById(2L));
        } else if (roles.stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN))) {
            listCreated.add(this.findById(2L)); // Client
            listCreated.add(this.findById(1L)); // Admin
            listCreated.add(this.findById(3L)); // Moderator
        } else if (roles.stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_MODERATOR))) {
            listCreated.add(this.findById(3L)); // Moderator
            listCreated.add(this.findById(2L)); // Client
        } else {
            roles.forEach(roleDTO -> listCreated.add(new Role(roleDTO.getId(), roleDTO.getRoleName())));
        }
        return listCreated;
    }
}
