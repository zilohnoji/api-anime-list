package com.donatoordep.anime_list_api.services.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomizedAnimeAlreadyInCartException {

    private Instant timestamp = Instant.now();
    private Integer status;
    private String error;
    private String path;
    private Long animeId;

    public CustomizedAnimeAlreadyInCartException() {
    }

    public CustomizedAnimeAlreadyInCartException(String error, Integer status, String path, Long id) {
        this.error = error;
        this.status = status;
        this.path = path;
        this.animeId = id;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
