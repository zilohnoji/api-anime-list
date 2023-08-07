package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.dto.AnimeOrderDetailsDTO;
import com.donatoordep.anime_list_api.dto.OrderDTO;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.AnimeOrder;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.repositories.AnimeOrderDetailsRepository;
import com.donatoordep.anime_list_api.repositories.AnimeOrderRepository;
import com.donatoordep.anime_list_api.repositories.AnimeRepository;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import com.donatoordep.anime_list_api.services.exceptions.AnimeAlreadyInCartException;
import com.donatoordep.anime_list_api.services.exceptions.CustomizedAnimeAlreadyInCartException;
import com.donatoordep.anime_list_api.services.exceptions.EntityNotAuthenticatedInSystemException;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public AnimeOrderDetailsDTO addAnimeInMyCart(OrderDTO dto) {
        User userAuthenticated = userService.authenticated();
        Anime anime = animeRepository.findById(dto.getAnimeId()).orElseThrow(NotFoundEntityException::new);

        AnimeOrderDetails animeOrderDetails = new AnimeOrderDetails(
                anime, dto.getEpisode(), dto.getStatus());

        containingAnime(userAuthenticated, dto); // Verifica se o anime já existe no carrinho

        animeOrderDetails = detailsRepository.save(animeOrderDetails);
        AnimeOrder animeOrder = new AnimeOrder(animeOrderDetails, userAuthenticated.getCart());
        animeOrderDetails.setAnimeOrder(animeOrder);
        userAuthenticated.getCart().getFavorites().add(animeOrder);
        userService.update(userAuthenticated);

        return new AnimeOrderDetailsDTO(animeOrderDetails);
    }

    public boolean containingAnime(User user, OrderDTO dto) {
        return user.getCart().getFavorites().stream()
                .anyMatch(obj1 -> obj1.getAnimeOrderDetails().stream()
                        .anyMatch(obj2 -> {
                            if (obj2.getAnime().getId().equals(dto.getAnimeId())) {
                                throw new AnimeAlreadyInCartException(obj2.getAnime().getId());
                            }
                            return obj2.getAnime().getId().equals(dto.getAnimeId());
                        }));
    }

    public AnimeOrderDetailsDTO findById(Long id) {
        return new AnimeOrderDetailsDTO(detailsRepository.findById(id)
                .orElseThrow(NotFoundEntityException::new));
    }
}
