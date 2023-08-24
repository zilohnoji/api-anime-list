package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.request.AnimeOrderDetailsRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeOrderDetailsResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.enums.StatusOrder;
import com.donatoordep.anime_list_api.repositories.AccountStatsRepository;
import com.donatoordep.anime_list_api.repositories.AnimeOrderDetailsRepository;
import com.donatoordep.anime_list_api.repositories.AnimeOrderRepository;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeInMyCartArgs;
import com.donatoordep.anime_list_api.services.business.rules.anime.addInMyCart.AddAnimeValidation;
import com.donatoordep.anime_list_api.utils.ConvertingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeOrderDetailsService {

    @Autowired
    private AnimeOrderDetailsRepository detailsRepository;

    @Autowired
    private AnimeOrderRepository animeOrderRepository;

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private List<AddAnimeValidation> validations;

    @Autowired
    private AccountStatsRepository accountStatsRepository;

    public AnimeOrderDetailsResponseDTO addAnimeInMyCart(AnimeOrderDetailsRequestDTO dto, User user) {
        validations.forEach(v -> v.verification(new AddAnimeInMyCartArgs(dto, animeRepository, user)));

        Anime anime = animeRepository.findById(dto.getAnimeId()).get();

        AnimeOrderDetails animeOrderDetails = new AnimeOrderDetails(
                anime, dto.getEpisode(), ConvertingType.convertStringToEnum(
                StatusOrder.class, dto.getStatus()));

        animeOrderDetails = detailsRepository.save(animeOrderDetails);
        AnimeOrder animeOrder = new AnimeOrder(animeOrderDetails, user.getCart());
        animeOrderDetails.setAnimeOrder(animeOrder);
        user.addAnime(animeOrder);

        userService.update(user);

        return new AnimeOrderDetailsResponseDTO(animeOrderDetails);
    }
}
