package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
