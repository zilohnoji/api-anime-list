package com.donatoordep.anime_list_api.entities;

import com.donatoordep.anime_list_api.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String imgUrl;
    private String authorName;
    private Status status;
    private Integer episodes;

    public Anime() {
    }

    public Anime(String description, String imgUrl, String authorName, Status status, Integer episodes) {
        this.description = description;
        this.imgUrl = imgUrl;
        this.authorName = authorName;
        this.status = status;
        this.episodes = episodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anime anime = (Anime) o;
        return Objects.equals(id, anime.id) && Objects.equals(description, anime.description) && Objects.equals(imgUrl, anime.imgUrl) && Objects.equals(authorName, anime.authorName) && Objects.equals(status, anime.status) && Objects.equals(episodes, anime.episodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, imgUrl, authorName, status, episodes);
    }
}
