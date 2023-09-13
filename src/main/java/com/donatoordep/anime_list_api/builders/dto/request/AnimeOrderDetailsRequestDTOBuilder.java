package com.donatoordep.anime_list_api.builders.dto.request;

import com.donatoordep.anime_list_api.dto.request.AnimeOrderDetailsRequestDTO;
import com.donatoordep.anime_list_api.dto.request.AnimeRequestDTO;

public class AnimeOrderDetailsRequestDTOBuilder {

    private AnimeOrderDetailsRequestDTO animeRequestDTO;

    private AnimeOrderDetailsRequestDTOBuilder() {
        this.animeRequestDTO = new AnimeOrderDetailsRequestDTO();
    }

    public static AnimeOrderDetailsRequestDTOBuilder builder(){
        return new AnimeOrderDetailsRequestDTOBuilder();
    }

    public AnimeOrderDetailsRequestDTO build(){
        return animeRequestDTO;
    }

    public AnimeOrderDetailsRequestDTOBuilder id(Long id){
        this.animeRequestDTO.setAnimeId(id);
        return this;
    }
    public AnimeOrderDetailsRequestDTOBuilder status(String status){
        this.animeRequestDTO.setStatus(status);
        return this;
    }

    public AnimeOrderDetailsRequestDTOBuilder episode(int episode) {
        this.animeRequestDTO.setEpisode(episode);
        return this;
    }
}
