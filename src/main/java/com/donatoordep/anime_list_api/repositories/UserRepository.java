package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE UPPER(u.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<User> findByName(String name, Pageable pageable);

    @Query("SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(CONCAT('%', :email, '%'))")
    UserDetails findByEmailForUserDetails(String email);

    @Query("SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(CONCAT('%', :email, '%'))")
    Optional<User> findEmailForUser(String email);
}
