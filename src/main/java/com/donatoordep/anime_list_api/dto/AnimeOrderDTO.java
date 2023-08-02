package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.enums.StatusOrder;

import java.util.Objects;

public class AnimeOrderDTO {

    private AnimeDTO anime;
    private Integer episode;
    private StatusOrder statusOrder;

    public AnimeOrderDTO() {
    }

    public AnimeOrderDTO(AnimeOrder entity) {
        this.statusOrder = entity.getStatusOrder();
        this.anime = new AnimeDTO(entity.getAnime());
        this.episode = entity.getEpisode();
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public Integer getEpisode() {
        return episode;
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
        return Objects.equals(anime, that.anime) && Objects.equals(episode, that.episode) && statusOrder == that.statusOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anime, episode, statusOrder);
    }

    @Override
    public String toString() {
        return "AnimeOrderDTO{" +
                "anime=" + anime +
                ", episode=" + episode +
                ", statusOrder=" + statusOrder +
                '}';
    }
}
