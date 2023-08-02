package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.enums.Status;

import java.util.Objects;

public class AnimeDTO {

    private Long id;
    private String title;
    private String description;
    private String imgUrl;
    private String authorName;
    private Status status;
    private Integer episodes;

    public AnimeDTO() {
    }

    public AnimeDTO(Anime entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        imgUrl = entity.getImgUrl();
        authorName = entity.getAuthorName();
        status = entity.getStatus();
        episodes = entity.getEpisodes();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        AnimeDTO anime = (AnimeDTO) o;
        return Objects.equals(id, anime.id) && Objects.equals(title, anime.title) && Objects.equals(description, anime.description) && Objects.equals(imgUrl, anime.imgUrl) && Objects.equals(authorName, anime.authorName) && status == anime.status && Objects.equals(episodes, anime.episodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, imgUrl, authorName, status, episodes);
    }
}
