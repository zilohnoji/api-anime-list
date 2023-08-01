package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
