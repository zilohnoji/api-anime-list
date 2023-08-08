package com.donatoordep.anime_list_api.dto.request;

import com.donatoordep.anime_list_api.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AnimeRequestDTO {

    private String title;
    private String description;
    @JsonProperty(value = "img_url")
    private String imgUrl;
    @JsonProperty(value = "author_name")
    private String authorName;
    private Status status;
    private Integer episodes;

    public AnimeRequestDTO() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeRequestDTO that = (AnimeRequestDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(authorName, that.authorName) && status == that.status && Objects.equals(episodes, that.episodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, imgUrl, authorName, status, episodes);
    }
}
