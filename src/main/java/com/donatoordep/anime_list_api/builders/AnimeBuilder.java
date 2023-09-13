package com.donatoordep.anime_list_api.builders;

import com.donatoordep.anime_list_api.dto.request.CategoriesRequestDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.Categories;
import com.donatoordep.anime_list_api.enums.Status;
import com.donatoordep.anime_list_api.repositories.CategoriesRepository;
import com.donatoordep.anime_list_api.services.AnimeService;

import java.util.ArrayList;
import java.util.List;

public class AnimeBuilder {

    private Anime anime;

    private AnimeBuilder() {
        this.anime = new Anime();
    }

    public static AnimeBuilder builder() {
        return new AnimeBuilder();
    }

    public Anime build() {
        return this.anime;
    }

    public AnimeBuilder id(Long id) {
        this.anime.setId(id);
        return this;
    }

    public AnimeBuilder title(String title) {
        this.anime.setTitle(title);
        return this;
    }

    public AnimeBuilder description(String description) {
        this.anime.setDescription(description);
        return this;
    }

    public AnimeBuilder categories(CategoriesRepository repository, List<CategoriesRequestDTO> dto) {
        dto.forEach(obj -> anime.addCategory(repository.findByName(obj.getCategory())));
        return this;
    }

    public AnimeBuilder imgUrl(String imgUrl) {
        this.anime.setImgUrl(imgUrl);
        return this;
    }

    public AnimeBuilder authorName(String authorName) {
        this.anime.setAuthorName(authorName);
        return this;
    }

    public AnimeBuilder status(Status status) {
        this.anime.setStatus(status);
        return this;
    }

    public AnimeBuilder episodes(int episodes) {
        this.anime.setEpisodes(episodes);
        return this;
    }
}