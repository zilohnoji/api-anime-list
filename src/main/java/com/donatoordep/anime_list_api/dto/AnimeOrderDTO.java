package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.enums.StatusOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimeOrderDTO {

    private List<AnimeOrderDetailsDTO> animeOrderDetails = new ArrayList<>();

    public AnimeOrderDTO() {
    }

    public AnimeOrderDTO(AnimeOrder entity) {
        this.animeOrderDetails = entity.getAnimeOrderDetails().stream()
                .map(AnimeOrderDetailsDTO::new).toList();
    }

    public List<AnimeOrderDetailsDTO> getAnimeOrderDetails() {
        return animeOrderDetails;
    }

    public void setAnimeOrderDetails(List<AnimeOrderDetailsDTO> animeOrderDetails) {
        this.animeOrderDetails = animeOrderDetails;
    }
}
