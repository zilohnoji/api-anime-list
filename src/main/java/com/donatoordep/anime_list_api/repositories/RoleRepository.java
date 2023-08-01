package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
