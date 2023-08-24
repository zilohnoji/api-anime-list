package com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.validations;

import com.donatoordep.anime_list_api.dto.response.AnimeResponseDTO;
import com.donatoordep.anime_list_api.services.AnimeService;
import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeInMyCartArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeValidation;
import com.donatoordep.anime_list_api.services.exceptions.IncompatibleEpisodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EpisodeNotIsValidValidation implements AddAnimeValidation {

    @Autowired
    AnimeService service;

    @Override
    public void verification(AddAnimeInMyCartArgs args) {
        AnimeResponseDTO anime = service.findById((args.dto().getAnimeId()));

        if (args.dto().getEpisode() > anime.getEpisodes()) {
            throw new IncompatibleEpisodeException();
        }
    }
}
