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

    @ManyToMany(mappedBy = "cart")
    private List<AnimeOrder> favorites = new ArrayList<>();

    public Cart() {
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
