package com.donatoordep.anime_list_api.builders.dto.request;

import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;
import com.donatoordep.anime_list_api.enums.Status;

public class AnimeRequestDTOBuilder {

    private AnimeRequestDTO animeRequestDTO;

    private AnimeRequestDTOBuilder() {
        this.animeRequestDTO = new AnimeRequestDTO();
    }

    public static AnimeRequestDTOBuilder builder(){
        return new AnimeRequestDTOBuilder();
    }

    public AnimeRequestDTO build(){
        return animeRequestDTO;
    }

    public AnimeRequestDTOBuilder description(String description){
        this.animeRequestDTO.setDescription(description);
        return this;
    }
    public AnimeRequestDTOBuilder title(String title){
        this.animeRequestDTO.setTitle(title);
        return this;
    }

    public AnimeRequestDTOBuilder imgUrl(String imgUrl) {
        this.animeRequestDTO.setImgUrl(imgUrl);
        return this;
    }
    public AnimeRequestDTOBuilder authorName(String authorName) {
        this.animeRequestDTO.setAuthorName(authorName);
        return this;
    }

    public AnimeRequestDTOBuilder status(Status status) {
        this.animeRequestDTO.setStatus(status);
        return this;
    }

    public AnimeRequestDTOBuilder episodes(Integer episodes) {
        this.animeRequestDTO.setEpisodes(episodes);
        return this;
    }
}
