package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.Cart;
import com.donatoordep.anime_list_api.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Long id;
    private List<AnimeOrderDTO> favorites = new ArrayList<>();
    private int totalAnimes;

    public CartDTO() {
    }

    public CartDTO(Cart entity) {
        this.id = entity.getId();
        this.favorites = entity.getFavorites().stream().map(AnimeOrderDTO::new).toList();
    }

    public void setFavorites(List<AnimeOrderDTO> favorites) {
        this.favorites = favorites;
    }

    public int getTotalAnimes() {
        return totalAnimes;
    }

    public void setTotalAnimes(int totalAnimes) {
        this.totalAnimes = totalAnimes;
    }

    public List<AnimeOrderDTO> getFavorites() {
        return favorites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
