package com.donatoordep.anime_list_api.services.business.rules.user.findByName.validations;

import com.donatoordep.anime_list_api.services.business.rules.user.findByName.FindByNameArgs;
import com.donatoordep.anime_list_api.services.business.rules.user.findByName.FindByNameValidation;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.springframework.stereotype.Component;

@Component
public class NameNotExistsInDatabaseValidation implements FindByNameValidation {

    @Override
    public void verification(FindByNameArgs args) {
        if (args.repository().findByName(args.name(), args.pageable()).isEmpty()) {
            throw new NotFoundEntityException();
        }
    }
}
