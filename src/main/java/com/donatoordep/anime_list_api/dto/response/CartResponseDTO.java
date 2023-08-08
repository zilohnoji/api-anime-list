package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.entities.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartResponseDTO {

    private int totalAnime;
    private List<AnimeOrderResponseDTO> favorites = new ArrayList<>();

    public CartResponseDTO(Cart entity) {
        totalAnime = entity.getTotalAnimes();
        favorites = entity.getFavorites().stream().map(AnimeOrderResponseDTO::new).toList();
    }

    public void addAnimeInCart(AnimeOrderResponseDTO animeOrderResponseDTO) {
        totalAnime += 1;
        favorites.add(animeOrderResponseDTO);
    }

    public int getTotalAnime() {
        return totalAnime;
    }

    public void setTotalAnime(int totalAnime) {
        this.totalAnime = totalAnime;
    }

    public List<AnimeOrderResponseDTO> getFavorites() {
        return favorites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartResponseDTO cartDTO = (CartResponseDTO) o;
        return totalAnime == cartDTO.totalAnime && Objects.equals(favorites, cartDTO.favorites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAnime, favorites);
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "totalAnimes=" + totalAnime +
                ", favorites=" + favorites +
                '}';
    }
}
