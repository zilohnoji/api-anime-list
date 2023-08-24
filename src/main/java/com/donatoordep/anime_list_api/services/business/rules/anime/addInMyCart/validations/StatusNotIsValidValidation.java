package com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.validations;

import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeInMyCartArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeValidation;
import com.donatoordep.anime_list_api.services.exceptions.InvalidEnumValueException;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class StatusNotIsValidValidation implements AddAnimeValidation {

    List<String> statusOrdersList = Stream.of(
            StatusOrder.DROPPED, StatusOrder.COMPLETED,
            StatusOrder.WATCHING, StatusOrder.PLAN_TO_WATCH).map(Enum::toString).toList();

    @Override
    public void verification(AddAnimeInMyCartArgs args) {

        if (!statusOrdersList.contains(args.dto().getStatus())) {
            throw new InvalidEnumValueException(
                    args.dto().getStatus(), "StatusOrder", statusOrdersList.toString());
        }
    }
}
