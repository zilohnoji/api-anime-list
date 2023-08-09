package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.request.AnimeOrderDetailsRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeOrderDetailsResponseDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.repositories.AccountStatsRepository;
import com.donatoordep.anime_list_api.repositories.AnimeOrderDetailsRepository;
import com.donatoordep.anime_list_api.repositories.AnimeOrderRepository;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.services.exceptions.AnimeAlreadyInCartException;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AccountStatsRepository accountStatsRepository;

    public AnimeOrderDetailsResponseDTO addAnimeInMyCart(AnimeOrderDetailsRequestDTO dto, User user) {
        Anime anime = animeRepository.findById(dto.getAnimeId()).orElseThrow(NotFoundEntityException::new);

        AnimeOrderDetails animeOrderDetails = new AnimeOrderDetails(
                anime, dto.getEpisode(), dto.getStatus());

        containingAnime(user, dto); // Verifica se o anime já existe no carrinho

        animeOrderDetails = detailsRepository.save(animeOrderDetails);
        AnimeOrder animeOrder = new AnimeOrder(animeOrderDetails, user.getCart());
        animeOrderDetails.setAnimeOrder(animeOrder);
        user.addAnime(animeOrder);

        userService.update(user);

        return new AnimeOrderDetailsResponseDTO(animeOrderDetails);
    }

    public boolean containingAnime(User user, AnimeOrderDetailsRequestDTO dto) {
        return user.getCart().getFavorites().stream()
                .anyMatch(obj1 -> obj1.getAnimeOrderDetails().stream()
                        .anyMatch(obj2 -> {
                            if (obj2.getAnime().getId().equals(dto.getAnimeId())) {
                                throw new AnimeAlreadyInCartException(obj2.getAnime().getId());
                            }
                            return obj2.getAnime().getId().equals(dto.getAnimeId());}));
    }
}
