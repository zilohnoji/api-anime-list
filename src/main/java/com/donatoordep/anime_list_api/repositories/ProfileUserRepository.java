package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.ProfileUser;
import com.donatoordep.anime_list_api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, Long> {
}
