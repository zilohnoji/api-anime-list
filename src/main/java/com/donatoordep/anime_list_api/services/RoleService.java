package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.enums.RoleName;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import com.donatoordep.anime_list_api.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundEntityException::new);
    }

    public List<Role> separateRolesWithHierarchy(RoleName role) {
        List<Role> listCreated = new ArrayList<>();

        Role admin = this.findById(1L);
        Role client = this.findById(2L);
        Role moderator = this.findById(3L);

        if (role == null) {
            listCreated.add(this.findById(2L));
        } else if (is(role, admin.getRoleName())) {
            listCreated.addAll(Arrays.asList(admin, client, moderator));
        } else if (is(role, moderator.getRoleName())) {
            listCreated.addAll(Arrays.asList(client, moderator));
        }
        return listCreated;
    }

    private static boolean is(RoleName role, RoleName roleName) {
        return role.equals(roleName);
    }
}
