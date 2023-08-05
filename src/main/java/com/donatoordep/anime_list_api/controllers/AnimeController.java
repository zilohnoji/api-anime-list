package com.donatoordep.anime_list_api.controllers;

import com.donatoordep.anime_list_api.dto.AnimeDTO;
import com.donatoordep.anime_list_api.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/v1/anime")
public class AnimeController {

    @Autowired
    private AnimeService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimeDTO> createAnime(@RequestBody AnimeDTO dto) {
        AnimeDTO objCreated = service.createAnime(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(objCreated.getId()).toUri()).body(objCreated);
    }
}
