package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.AccountStats;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class AccountStatsDTO {

    @JsonIgnore
    private Long id;
    private Integer watching;
    private Integer completed;
    private Integer dropped;
    private Integer planToWatch;

    public AccountStatsDTO() {
    }

    public AccountStatsDTO(AccountStats entity) {
        this.watching = entity.getWatching();
        this.completed = entity.getCompleted();
        this.dropped = entity.getDropped();
        this.planToWatch = entity.getPlanToWatch();
        this.id = entity.getId();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatsDTO that = (AccountStatsDTO) o;
        return Objects.equals(watching, that.watching) && Objects.equals(completed, that.completed) && Objects.equals(dropped, that.dropped) && Objects.equals(planToWatch, that.planToWatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(watching, completed, dropped, planToWatch);
    }

    @Override
    public String toString() {
        return "AccountStatsDTO{" +
                "watching=" + watching +
                ", completed=" + completed +
                ", dropped=" + dropped +
                ", planToWatch=" + planToWatch +
                '}';
    }
}
