package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.enums.StatusOrder;
import jakarta.persistence.*;

public class AnimeOrderDetailsDTO {

    private Long id;
    private Anime anime;
    private Integer episode;
    private StatusOrder statusOrder;

    public AnimeOrderDetailsDTO() {
    }

    public AnimeOrderDetailsDTO(Long id, Anime anime, Integer episode, StatusOrder statusOrder) {
        this.id = id;
        this.anime = anime;
        this.episode = episode;
        this.statusOrder = statusOrder;
    }

    public AnimeOrderDetailsDTO(AnimeOrderDetails entity) {
        this.id = entity.getId();
        this.anime = entity.getAnime();
        this.episode = entity.getEpisode();
        this.statusOrder = entity.getStatusOrder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }
}
