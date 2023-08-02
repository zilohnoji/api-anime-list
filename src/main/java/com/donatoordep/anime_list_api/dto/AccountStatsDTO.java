package com.donatoordep.anime_list_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class AccountStatsDTO {

    private Long id;
    private Integer watching;
    private Integer completed;
    private Integer dropped;
    private Integer planToWatch;
    @JsonIgnore
    private ProfileUserDTO perfilUser;

    public AccountStatsDTO() {
    }

    public AccountStatsDTO(Integer watching, Integer completed, Integer dropped, Integer planToWatch) {
        this.watching = watching;
        this.completed = completed;
        this.dropped = dropped;
        this.planToWatch = planToWatch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWatching() {
        return watching;
    }

    public void setWatching(Integer watching) {
        this.watching = watching;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getDropped() {
        return dropped;
    }

    public void setDropped(Integer dropped) {
        this.dropped = dropped;
    }

    public Integer getPlanToWatch() {
        return planToWatch;
    }

    public void setPlanToWatch(Integer planToWatch) {
        this.planToWatch = planToWatch;
    }

    public ProfileUserDTO getPerfilUser() {
        return perfilUser;
    }

    public void setPerfilUser(ProfileUserDTO perfilUser) {
        this.perfilUser = perfilUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatsDTO that = (AccountStatsDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(watching, that.watching) && Objects.equals(completed, that.completed) && Objects.equals(dropped, that.dropped) && Objects.equals(planToWatch, that.planToWatch) && Objects.equals(perfilUser, that.perfilUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, watching, completed, dropped, planToWatch, perfilUser);
    }

    @Override
    public String toString() {
        return "AccountStats{" +
                "id=" + id +
                ", watching=" + watching +
                ", completed=" + completed +
                ", dropped=" + dropped +
                ", planToWatch=" + planToWatch +
                ", perfilUser=" + perfilUser +
                '}';
    }
}
