package com.donatoordep.anime_list_api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<AnimeOrder> favorites = new ArrayList<>();

    private int totalAnimes;

    public Cart() {
    }

    public Cart(Long id, List<AnimeOrder> favorites, int totalAnimes) {
        this.id = id;
        this.favorites = favorites;
        this.totalAnimes = totalAnimes;
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

    public Cart(Long id, User user) {
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
