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
import com.donatoordep.anime_list_api.services.exceptions.EntityNotAuthenticatedInSystemException;
import com.donatoordep.anime_list_api.services.exceptions.NotFoundEntityException;
import jakarta.persistence.criteria.Order;
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
    private UserRepository userRepository;

    public AnimeOrderDetailsDTO addAnimeInMyCart(OrderDTO dto) {
        User userAuthenticated = userService.authenticated();
        Anime anime = animeRepository.findById(dto.getAnimeId()).orElseThrow(NotFoundEntityException::new);

        AnimeOrderDetails animeOrderDetails = new AnimeOrderDetails(
                dto.getAnimeId(), anime, dto.getEpisode(), dto.getStatus());

        AnimeOrder animeOrder = new AnimeOrder(animeOrderDetails);

        userAuthenticated.getCart().getFavorites().add(animeOrder);
        userRepository.save(userAuthenticated);

        return new AnimeOrderDetailsDTO(animeOrderDetails);
    }

    public AnimeOrderDetails findById(Long id) {
        return (detailsRepository.findById(id)).orElseThrow(NotFoundEntityException::new);
    }
}
