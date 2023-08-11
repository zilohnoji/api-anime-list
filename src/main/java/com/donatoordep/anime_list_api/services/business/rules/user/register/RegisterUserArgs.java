package com.donatoordep.anime_list_api.services.business.rules.user.register;

import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.repositories.UserRepository;

public record RegisterUserArgs(UserRequestDTO userDTO, UserRepository userRepository) {
}
