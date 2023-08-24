package com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.validations;

import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeInMyCartArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeValidation;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.springframework.stereotype.Component;

@Component
public class AnimeNotExistsValidation implements AddAnimeValidation {

    @Override
    public void verification(AddAnimeInMyCartArgs args) {
        if (args.repository().findById(args.dto().getAnimeId()).isEmpty()) {
            throw new NotFoundEntityException();
        }
    }
}
