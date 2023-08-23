package com.donatoordep.anime_list_api.services.business.rules.anime.service.validations;

import com.donatoordep.anime_list_api.services.business.rules.anime.service.AnimeArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.service.AnimeValidation;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.springframework.stereotype.Component;

@Component
public class NotFoundAnimeWhenFindAll implements AnimeValidation {

    @Override
    public void verification(AnimeArgs args) {
            if(args.repository().findByName(args.name()).isEmpty()){
                throw new NotFoundEntityException();
            }
    }
}
