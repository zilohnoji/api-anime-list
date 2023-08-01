package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
