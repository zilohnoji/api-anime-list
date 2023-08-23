package com.donatoordep.anime_list_api.services.business.rules.user.findByName;

import com.donatoordep.anime_list_api.repositories.UserRepository;
import org.springframework.data.domain.Pageable;

public record FindByNameArgs(UserRepository repository, String name, Pageable pageable) {
}
