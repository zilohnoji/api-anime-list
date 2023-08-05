package com.donatoordep.anime_list_api.services.exceptions;

import java.time.Instant;

public class CustomizedException {

    private Instant timestamp = Instant.now();
    private Integer status;
    private String error;
    private String path;

    public CustomizedException() {
    }

    public CustomizedException(String error, Integer status, String path) {
        this.error = error;
        this.status = status;
        this.path = path;
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
