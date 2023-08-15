package com.donatoordep.anime_list_api.services.business.rules.user.register.validations;

import com.donatoordep.anime_list_api.services.business.rules.user.register.RegisterUserArgs;
import com.donatoordep.anime_list_api.services.business.rules.user.register.RegisterUserValidation;
import com.donatoordep.anime_list_api.services.exceptions.WeakPasswordException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class PasswordSizeValidation implements RegisterUserValidation {

    @Override
    public void verification(RegisterUserArgs args) {

        if(args.userDTO().getPassword().length() < 5) {
            throw new WeakPasswordException();
        }
    }
}
