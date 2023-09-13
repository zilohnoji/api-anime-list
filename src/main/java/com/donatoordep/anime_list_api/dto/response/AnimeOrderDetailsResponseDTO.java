package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeOrderDetailsResponseDTO {

    private AnimeResponseDTO anime;
    private Integer episode;
    @JsonProperty(value = "status")
    private StatusOrder statusOrder;

    public AnimeOrderDetailsResponseDTO() {
    }

    public AnimeOrderDetailsResponseDTO(AnimeOrderDetails entity) {
        anime = new AnimeResponseDTO(entity.getAnime());
        episode = entity.getEpisode();
        statusOrder = entity.getStatusOrder();
    }

    public AnimeResponseDTO getAnime() {
        return anime;
    }

    public void setAnime(AnimeResponseDTO anime) {
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
