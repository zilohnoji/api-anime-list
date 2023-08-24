package com.donatoordep.anime_list_api.dto.request;

import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AnimeOrderDetailsRequestDTO {

    @JsonProperty(value = "anime_id")
    @NotNull(message = "The anime_id is required")
    @Positive(message = "The value of anime_id should be positive")
    private Long animeId;
    @NotNull(message = "The status is required")
    private String status;
    @NotNull(message = "The episode is required")
    @Positive(message = "The value of episode should be positive")
    private int episode;

    public AnimeOrderDetailsRequestDTO(Long animeId, String status, int episode) {
        this.animeId = animeId;
        this.status = status;
        this.episode = episode;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
