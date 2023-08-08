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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.EAGER)
    private List<AnimeOrder> favorites = new ArrayList<>();

    private int totalAnimes;

    public Cart() {
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

    public void add(AnimeOrder anime) {
        favorites.add(anime);
        totalAnimes += 1;
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
