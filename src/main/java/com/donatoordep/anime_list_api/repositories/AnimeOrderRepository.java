package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.AnimeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeOrderRepository extends JpaRepository<AnimeOrder, Long> {
}
