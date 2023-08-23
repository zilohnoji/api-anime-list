package com.donatoordep.anime_list_api.services.business.rules.anime.register.validations;

import com.donatoordep.anime_list_api.services.business.rules.anime.register.AddAnimeInMyCartArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.register.RegisterAnimeValidation;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.springframework.stereotype.Component;

@Component
public class AnimeNotExistsValidation implements RegisterAnimeValidation {

    @Override
    public void verification(AddAnimeInMyCartArgs args) {
        if (args.repository().findById(args.dto().getAnimeId()).isEmpty()) {
            throw new NotFoundEntityException();
        }
    }
}
