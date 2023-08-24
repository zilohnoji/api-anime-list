package com.donatoordep.anime_list_api.builders.dto.response;

import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.enums.Status;

public class AnimeResponseDTOBuilder {
    
    private AnimeResponseDTO animeResponseDTO;

    private AnimeResponseDTOBuilder() {
        this.animeResponseDTO = new AnimeResponseDTO();
    }

    public static AnimeResponseDTOBuilder builder(){
        return new AnimeResponseDTOBuilder();
    }

    public AnimeResponseDTO build(){
        return animeResponseDTO;
    }

    public AnimeResponseDTOBuilder description(String description){
        this.animeResponseDTO.setDescription(description);
        return this;
    }

    public AnimeResponseDTOBuilder id(Long id) {
        this.animeResponseDTO.setId(id);
        return this;
    }

    public AnimeResponseDTOBuilder title(String title){
        this.animeResponseDTO.setTitle(title);
        return this;
    }

    public AnimeResponseDTOBuilder imgUrl(String imgUrl) {
        this.animeResponseDTO.setImgUrl(imgUrl);
        return this;
    }
    public AnimeResponseDTOBuilder authorName(String authorName) {
        this.animeResponseDTO.setAuthorName(authorName);
        return this;
    }

    public AnimeResponseDTOBuilder status(Status status) {
        this.animeResponseDTO.setStatus(status);
        return this;
    }

    public AnimeResponseDTOBuilder episodes(Integer episodes) {
        this.animeResponseDTO.setEpisodes(episodes);
        return this;
    }
}
