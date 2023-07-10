package com.donatoordep.anime_list_api.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AccountStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer watching;
    private Integer completed;
    private Integer dropped;
    private Integer planToWatch;

    @OneToOne(mappedBy = "animetStats")
    private User user;

    public AccountStats() {
    }

    public AccountStats(Integer watching, Integer completed, Integer dropped, Integer planToWatch, User user) {
        this.watching = watching;
        this.completed = completed;
        this.dropped = dropped;
        this.planToWatch = planToWatch;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStats that = (AccountStats) o;
        return Objects.equals(id, that.id) && Objects.equals(watching, that.watching) && Objects.equals(completed, that.completed) && Objects.equals(dropped, that.dropped) && Objects.equals(planToWatch, that.planToWatch) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, watching, completed, dropped, planToWatch, user);
    }
}
