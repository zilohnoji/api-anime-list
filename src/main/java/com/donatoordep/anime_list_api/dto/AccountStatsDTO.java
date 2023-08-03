package com.donatoordep.anime_list_api.dto;

import com.donatoordep.anime_list_api.entities.AccountStats;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class AccountStatsDTO {

    @JsonIgnore
    private Long id;
    private int watching;
    private int completed;
    private int dropped;
    private int planToWatch;

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

    public int getWatching() {
        return watching;
    }

    public void setWatching(int watching) {
        this.watching = watching;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getDropped() {
        return dropped;
    }

    public void setDropped(int dropped) {
        this.dropped = dropped;
    }

    public int getPlanToWatch() {
        return planToWatch;
    }

    public void setPlanToWatch(int planToWatch) {
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
