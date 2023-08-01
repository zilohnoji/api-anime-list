package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Long id;

    @JsonIgnore
    private User user;
    private List<AnimeOrder> favorites = new ArrayList<>();
    private int totalAnimes;

    public CartDTO() {
    }

    public void setFavorites(List<AnimeOrder> favorites) {
        this.favorites = favorites;
    }

    public int getTotalAnimes() {
        return totalAnimes;
    }

    public void setTotalAnimes(int totalAnimes) {
        this.totalAnimes = totalAnimes;
    }

    public CartDTO(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public List<AnimeOrder> getFavorites() {
        return favorites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
