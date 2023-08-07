package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.dto.AnimeOrderDetailsDTO;
import com.donatoordep.anime_list_api.dto.OrderDTO;
import com.donatoordep.anime_list_api.repositories.AnimeOrderDetailsRepository;
import com.donatoordep.anime_list_api.services.AnimeOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderController {

    @Autowired
    private AnimeOrderDetailsService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimeOrderDetailsDTO> addAnimeInMyCart(@RequestBody OrderDTO dto) {
        return ResponseEntity.ok().body(service.addAnimeInMyCart(dto));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimeOrderDetailsDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
