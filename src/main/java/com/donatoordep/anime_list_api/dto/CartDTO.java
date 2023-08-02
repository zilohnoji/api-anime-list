package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartDTO {

    private int totalAnimes;
    private List<AnimeOrderDTO> favorites = new ArrayList<>();

    public CartDTO() {
    }

    public CartDTO(Cart entity) {
        this.favorites = entity.getFavorites().stream().map(AnimeOrderDTO::new).toList();
        this.totalAnimes = entity.getTotalAnimes();
    }

    public void setFavorites(List<AnimeOrderDTO> favorites) {
        this.favorites = favorites;
    }

    public int getTotalAnimes() {
        return totalAnimes;
    }

    public void setTotalAnimes(int totalAnimes) {
        this.totalAnimes = totalAnimes;
    }

    public List<AnimeOrderDTO> getFavorites() {
        return favorites;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDTO cartDTO = (CartDTO) o;
        return totalAnimes == cartDTO.totalAnimes && Objects.equals(favorites, cartDTO.favorites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalAnimes, favorites);
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "totalAnimes=" + totalAnimes +
                ", favorites=" + favorites +
                '}';
    }
}
