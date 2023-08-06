package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.enums.StatusOrder;

public class OrderDTO {

    private Long animeId;
    private StatusOrder status;
    private int episode;

    public OrderDTO() {

    }

    public StatusOrder getStatus() {
        return status;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public OrderDTO(Long id) {
        this.animeId = id;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }
}
