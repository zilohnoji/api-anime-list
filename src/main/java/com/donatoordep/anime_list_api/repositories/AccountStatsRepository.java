package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.AccountStats;
import com.donatoordep.anime_list_api.entities.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStatsRepository extends JpaRepository<AccountStats, Long> {
}
