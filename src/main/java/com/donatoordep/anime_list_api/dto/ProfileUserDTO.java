package com.donatoordep.anime_list_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProfileUserDTO {

    private Long id;

    @JsonIgnore
    private UserDTO user;

    private AccountStatsDTO animeStats;
    private String imgUrl;
    private String bio;

    public ProfileUserDTO() {
    }

    public ProfileUserDTO(UserDTO user, AccountStatsDTO animeStats, String imgUrl, String bio) {
        this.user = user;
        this.animeStats = animeStats;
        this.imgUrl = imgUrl;
        this.bio = bio;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AccountStatsDTO getAnimeStats() {
        return animeStats;
    }

    public void setAnimeStats(AccountStatsDTO animeStats) {
        this.animeStats = animeStats;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
