package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.ProfileUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class ProfileUserDTO {

    @JsonIgnore
    private Long id;
    private String imgUrl;
    private String bio;
    private AccountStatsDTO accountStats;

    public ProfileUserDTO() {
    }

    public ProfileUserDTO(ProfileUser entity) {
        imgUrl = entity.getImgUrl();
        id = entity.getId();
        bio = entity.getBio();
    }

    public AccountStatsDTO getAccountStats() {
        return accountStats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountStats(AccountStatsDTO accountStats) {
        this.accountStats = accountStats;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileUserDTO that = (ProfileUserDTO) o;
        return Objects.equals(imgUrl, that.imgUrl) && Objects.equals(bio, that.bio) && Objects.equals(accountStats, that.accountStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgUrl, bio, accountStats);
    }

    @Override
    public String toString() {
        return "ProfileUserDTO{" +
                "imgUrl='" + imgUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", accountStats=" + accountStats +
                '}';
    }
}
