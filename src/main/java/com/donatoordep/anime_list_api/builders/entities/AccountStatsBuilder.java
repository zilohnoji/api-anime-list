package com.donatoordep.anime_list_api.builders.entities;

import com.donatoordep.anime_list_api.entities.AccountStats;

public class AccountStatsBuilder {

    private AccountStats accountStats;

    private AccountStatsBuilder() {
        this.accountStats = new AccountStats();
    }

    public static AccountStatsBuilder builder() {
        return new AccountStatsBuilder();
    }

    public AccountStats build() {
        return this.accountStats;
    }

    public AccountStatsBuilder id(Long id) {
        this.accountStats.setId(id);
        return this;
    }

    public AccountStatsBuilder watching(int watching) {
        this.accountStats.setWatching(watching);
        return this;
    }

    public AccountStatsBuilder completed(int completed) {
        this.accountStats.setCompleted(completed);
        return this;
    }

    public AccountStatsBuilder dropped(int dropped) {
        this.accountStats.setDropped(dropped);
        return this;
    }

    public AccountStatsBuilder planToWatch(int planToWatch) {
        this.accountStats.setPlanToWatch(planToWatch);
        return this;
    }
}
