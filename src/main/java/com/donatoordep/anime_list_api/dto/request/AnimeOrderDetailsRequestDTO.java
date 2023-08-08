package com.donatoordep.anime_list_api.dto.request;

import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeOrderDetailsRequestDTO {

    @JsonProperty(value = "anime_id")
    private Long animeId;
    private StatusOrder status;
    private int episode;

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
