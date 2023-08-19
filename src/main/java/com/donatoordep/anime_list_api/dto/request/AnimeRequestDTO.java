package com.donatoordep.anime_list_api.dto.request;

import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AnimeRequestDTO {

    @NotBlank(message = "The title is required")
    @Size(min = 5, max = 150, message = "Title size interval is (5 - 150)")
    private String title;
    @NotBlank(message = "The description is required")
    @Size(min = 20, max = 200, message = "Description size interval is (20 - 200)")
    private String description;
    @JsonProperty(value = "img_url")
    private String imgUrl;
    @JsonProperty(value = "author_name")
    @NotBlank(message = "The author_name is required")
    private String authorName;
    @NotNull(message = "The status is required")
    private Status status;
    @Positive(message = "The value of episodes should be positive")
    @NotNull(message = "The episodes is required")
    private Integer episodes;

    public AnimeRequestDTO() {
    }

    public AnimeRequestDTO(Anime entity) {
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.imgUrl = entity.getImgUrl();
        this.authorName = entity.getAuthorName();
        this.status = entity.getStatus();
        this.episodes = entity.getEpisodes();
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
