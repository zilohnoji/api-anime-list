package com.donatoordep.anime_list_api.controllers;


import com.donatoordep.anime_list_api.dto.UserDTO;
import com.donatoordep.anime_list_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserDTO>> findByName(@RequestParam(name = "name") String name, Pageable pageable) {
        return ResponseEntity.ok().body(service.findByName(name, pageable));
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> myProfile() {
        return ResponseEntity.ok().body(service.me());
    }
}
