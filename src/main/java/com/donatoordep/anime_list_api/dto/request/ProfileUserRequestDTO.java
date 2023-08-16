package com.donatoordep.anime_list_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileUserRequestDTO {

    @JsonProperty(value = "img_url")
    private String imgUrl;
    private String bio;

    private ProfileUserRequestDTO() {
    }

    public ProfileUserRequestDTO(String imgUrl, String bio) {
        this.imgUrl = imgUrl;
        this.bio = bio;
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
}
