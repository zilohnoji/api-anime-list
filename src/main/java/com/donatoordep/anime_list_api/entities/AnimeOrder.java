package com.donatoordep.anime_list_api.entities;

import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AnimeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Integer episode;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    public AnimeOrder() {
    }

    public AnimeOrder(StatusOrder statusOrder, Integer episode, Anime anime) {
        this.statusOrder = statusOrder;
        this.episode = episode;
        this.anime = anime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeOrder that = (AnimeOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(statusOrder, that.statusOrder) && Objects.equals(episode, that.episode) && Objects.equals(anime, that.anime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusOrder, episode, anime);
    }
}
