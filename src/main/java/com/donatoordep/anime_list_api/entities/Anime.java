package com.donatoordep.anime_list_api.entities;

import com.donatoordep.anime_list_api.enums.Status;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String imgUrl;
    private String authorName;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer episodes;

    public Anime() {
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

    public void setTitle(String title) {
        this.title = title;
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
        return Objects.equals(id, anime.id) && Objects.equals(title, anime.title) && Objects.equals(description, anime.description) && Objects.equals(imgUrl, anime.imgUrl) && Objects.equals(authorName, anime.authorName) && status == anime.status && Objects.equals(episodes, anime.episodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, imgUrl, authorName, status, episodes);
    }
}
