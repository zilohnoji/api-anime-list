package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeOrderDetailsRepository extends JpaRepository<AnimeOrderDetails, Long> {
}
