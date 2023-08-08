package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnimeOrderResponseDTO {

    @JsonProperty(value = "anime_order_details")
    private List<AnimeOrderDetailsResponseDTO> animeOrderDetails = new ArrayList<>();

    public AnimeOrderResponseDTO() {
    }

    public AnimeOrderResponseDTO(AnimeOrder entity) {
        animeOrderDetails = entity.getAnimeOrderDetails().stream()
                .map(AnimeOrderDetailsResponseDTO::new).toList();
    }

    public void addAnimeOrderDetails(AnimeOrderDetailsResponseDTO dto) {
        animeOrderDetails.add(dto);
    }

    public List<AnimeOrderDetailsResponseDTO> getAnimeOrderDetails() {
        return animeOrderDetails;
    }
}
