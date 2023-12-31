package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.Categories;
import com.donatoordep.anime_list_api.enums.Category;
import com.donatoordep.anime_list_api.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimeResponseDTO {

    private Long id;
    private String title;
    private String description;
    @JsonProperty(value = "img_url")
    private String imgUrl;
    @JsonProperty(value = "author_name")
    private String authorName;
    private Status status;
    private Integer episodes;
    private List<Categories> categories = new ArrayList<>();

    public AnimeResponseDTO() {
    }

    public AnimeResponseDTO(Anime entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        imgUrl = entity.getImgUrl();
        authorName = entity.getAuthorName();
        status = entity.getStatus();
        episodes = entity.getEpisodes();
        categories = entity.getCategories();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void addCategories(Categories category) {
        this.categories.add(category);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
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
        AnimeResponseDTO anime = (AnimeResponseDTO) o;
        return Objects.equals(id, anime.id) && Objects.equals(title, anime.title) && Objects.equals(description, anime.description) && Objects.equals(imgUrl, anime.imgUrl) && Objects.equals(authorName, anime.authorName) && status == anime.status && Objects.equals(episodes, anime.episodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, imgUrl, authorName, status, episodes);
    }
}
