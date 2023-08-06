package com.donatoordep.anime_list_api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AnimeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "animeOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AnimeOrderDetails> animeOrderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public AnimeOrder() {
    }

    public AnimeOrder(List<AnimeOrderDetails> anime) {
        this.animeOrderDetails = anime;
    }

    public AnimeOrder(AnimeOrderDetails anime, Cart cart) {
        this.animeOrderDetails.add(anime);
        this.cart = cart;
    }

    public List<AnimeOrderDetails> getAnimeOrderDetails() {
        return animeOrderDetails;
    }

    public void setAnimeOrderDetails(List<AnimeOrderDetails> animeOrderDetails) {
        this.animeOrderDetails = animeOrderDetails;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
