package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE UPPER(u.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<User> findByName(String name);

    UserDetails findByEmail(String email);

    @Query("SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(CONCAT('%', :email, '%'))")
    User findEmailForUserAuthenticate(String email);
}
