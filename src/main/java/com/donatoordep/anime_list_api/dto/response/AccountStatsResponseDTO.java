package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.entities.AccountStats;

import java.util.Objects;

public class AccountStatsResponseDTO {

    private int watching;
    private int completed;
    private int dropped;
    private int planToWatch;

    public AccountStatsResponseDTO() {
    }

    public AccountStatsResponseDTO(AccountStats animeStats) {
        watching = animeStats.getWatching();
        completed = animeStats.getCompleted();
        dropped = animeStats.getDropped();
        planToWatch = animeStats.getPlanToWatch();
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
        AccountStatsResponseDTO that = (AccountStatsResponseDTO) o;
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
