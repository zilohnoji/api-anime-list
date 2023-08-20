package com.donatoordep.anime_list_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AccountStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int watching;
    private int completed;
    private int dropped;
    private int planToWatch;

    @JsonIgnore
    @OneToOne(mappedBy = "animeStats")
    private ProfileUser perfilUser;

    public AccountStats() {
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

    public ProfileUser getPerfilUser() {
        return perfilUser;
    }

    public void setPerfilUser(ProfileUser perfilUser) {
        this.perfilUser = perfilUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStats that = (AccountStats) o;
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
