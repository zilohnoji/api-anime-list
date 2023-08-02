package com.donatoordep.anime_list_api.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ProfileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "profile")
    private User user;

    @OneToOne
    @JoinColumn(name = "stats_id")
    private AccountStats animeStats;

    private String imgUrl;

    private String bio;

    public ProfileUser() {
    }

    public ProfileUser(Long id, String imgUrl, String bio) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountStats getAnimeStats() {
        return animeStats;
    }

    public void setAnimeStats(AccountStats animeStats) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileUser that = (ProfileUser) o;
        return Objects.equals(user, that.user) && Objects.equals(animeStats, that.animeStats) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(bio, that.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, animeStats, imgUrl, bio);
    }
}
