package com.donatoordep.anime_list_api.builders.dto.response;

import com.donatoordep.anime_list_api.dto.response.AccountStatsResponseDTO;
import com.donatoordep.anime_list_api.dto.response.ProfileUserResponseDTO;
import com.donatoordep.anime_list_api.entities.AccountStats;

public class ProfileUserResponseDTOBuilder {

    private ProfileUserResponseDTO profileUserResponseDTO;

    private ProfileUserResponseDTOBuilder() {
        this.profileUserResponseDTO = new ProfileUserResponseDTO();
    }

    public static ProfileUserResponseDTOBuilder builder() {
        return new ProfileUserResponseDTOBuilder();
    }

    public ProfileUserResponseDTO build() {
        return this.profileUserResponseDTO;
    }

    public ProfileUserResponseDTOBuilder imgUrl(String img) {
        this.profileUserResponseDTO.setImgUrl(img);
        return this;
    }

    public ProfileUserResponseDTOBuilder accountStats(AccountStatsResponseDTO accountStatsResponseDTO) {
        this.profileUserResponseDTO.setAccountStats(accountStatsResponseDTO);
        return this;
    }

    public ProfileUserResponseDTOBuilder accountStats(
            int watching, int completed, int dropped, int planToWatch) {
        this.profileUserResponseDTO.setAccountStats(new AccountStatsResponseDTO());
        this.profileUserResponseDTO.getAccountStats().setCompleted(completed);
        this.profileUserResponseDTO.getAccountStats().setWatching(watching);
        this.profileUserResponseDTO.getAccountStats().setDropped(dropped);
        this.profileUserResponseDTO.getAccountStats().setPlanToWatch(planToWatch);

        return this;
    }

    public ProfileUserResponseDTOBuilder accountStats(
            AccountStats accountStats) {
        this.profileUserResponseDTO.setAccountStats(new AccountStatsResponseDTO());
        this.profileUserResponseDTO.getAccountStats().setCompleted(accountStats.getCompleted());
        this.profileUserResponseDTO.getAccountStats().setWatching(accountStats.getWatching());
        this.profileUserResponseDTO.getAccountStats().setDropped(accountStats.getDropped());
        this.profileUserResponseDTO.getAccountStats().setPlanToWatch(accountStats.getPlanToWatch());

        return this;
    }

    public ProfileUserResponseDTOBuilder bio(String bio) {
        this.profileUserResponseDTO.setBio(bio);
        return this;
    }
}
