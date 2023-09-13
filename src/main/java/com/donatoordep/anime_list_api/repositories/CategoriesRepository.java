package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Query("SELECT u FROM Categories u WHERE UPPER(u.category) LIKE(UPPER(:title))")
    Categories findByName(String title);
}
