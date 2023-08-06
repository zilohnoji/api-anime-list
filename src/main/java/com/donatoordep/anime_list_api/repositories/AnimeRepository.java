package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    @Query("SELECT u FROM Anime u WHERE UPPER(u.title) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Anime> findByName(String name);
}
