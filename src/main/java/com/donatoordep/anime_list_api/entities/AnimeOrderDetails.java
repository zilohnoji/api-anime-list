package com.donatoordep.anime_list_api.entities;

import com.donatoordep.anime_list_api.enums.StatusOrder;
import jakarta.persistence.*;

@Entity
public class AnimeOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private AnimeOrder animeOrder;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    private int episode;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    public AnimeOrderDetails() {
    }

    public AnimeOrderDetails(Anime anime, int episode, StatusOrder statusOrder) {
        this.anime = anime;
        this.episode = episode;
        this.statusOrder = statusOrder;
    }

    public AnimeOrderDetails(Long id, Anime anime, int episode, StatusOrder statusOrder) {
        this.id = id;
        this.anime = anime;
        this.episode = episode;
        this.statusOrder = statusOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimeOrder getAnimeOrder() {
        return animeOrder;
    }

    public void setAnimeOrder(AnimeOrder animeOrder) {
        this.animeOrder = animeOrder;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }
}
