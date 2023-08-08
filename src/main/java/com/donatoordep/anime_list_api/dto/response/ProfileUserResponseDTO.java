package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.entities.ProfileUser;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProfileUserResponseDTO {

    @JsonProperty(value = "img_url")
    private String imgUrl;
    private String bio;
    @JsonProperty(value = "account_stats")
    private AccountStatsResponseDTO accountStats;

    public ProfileUserResponseDTO() {
    }

    public ProfileUserResponseDTO(ProfileUser entity) {
        imgUrl = entity.getImgUrl();
        bio = entity.getBio();
    }

    public AccountStatsResponseDTO getAccountStats() {
        return accountStats;
    }

    public void setAccountStats(AccountStatsResponseDTO accountStats) {
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
        ProfileUserResponseDTO that = (ProfileUserResponseDTO) o;
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
