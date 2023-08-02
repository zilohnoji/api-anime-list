package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.Cart;
import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class AnimeOrderDTO {

    private Long id;
    private AnimeDTO anime;
    private Integer episode;
    private StatusOrder statusOrder;

    public AnimeOrderDTO() {
    }

    public AnimeOrderDTO(StatusOrder statusOrder, Integer episode, AnimeDTO anime) {
        this.statusOrder = statusOrder;
        this.episode = episode;
        this.anime = anime;
    }

    public AnimeOrderDTO(AnimeOrder entity) {
        this.statusOrder = entity.getStatusOrder();
        this.anime = new AnimeDTO(entity.getAnime());
        this.episode = entity.getEpisode();
        this.id = entity.getId();
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

    public AnimeDTO getAnime() {
        return anime;
    }

    public void setAnime(AnimeDTO anime) {
        this.anime = anime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeOrderDTO that = (AnimeOrderDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(statusOrder, that.statusOrder) && Objects.equals(episode, that.episode) && Objects.equals(anime, that.anime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusOrder, episode, anime);
    }
}
