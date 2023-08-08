package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.dto.request.AnimeOrderDetailsRequestDTO;
import com.donatoordep.anime_list_api.dto.response.AnimeOrderDetailsResponseDTO;
import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.services.AnimeOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderController {

    @Autowired
    private AnimeOrderDetailsService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimeOrderDetailsResponseDTO> addAnimeInMyCart(
            @RequestBody AnimeOrderDetailsRequestDTO dto, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(service.addAnimeInMyCart(dto, user));
    }
}
