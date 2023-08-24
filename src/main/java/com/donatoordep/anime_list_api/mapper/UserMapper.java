package com.donatoordep.anime_list_api.mapper;

import com.donatoordep.anime_list_api.builders.UserBuilder;
import com.donatoordep.anime_list_api.dto.request.UserRequestDTO;
import com.donatoordep.anime_list_api.dto.response.UserResponseDTO;
import com.donatoordep.anime_list_api.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public User convertUserRequestDTOToUser(UserRequestDTO dto) {
        return UserBuilder.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .profile(dto.getProfile().getImgUrl(), dto.getProfile().getBio())
                .password(dto.getPassword())
                .cart()
                .build();
    }

    public UserResponseDTO convertUserToUserResponseDTO(User entity) {
        return new UserResponseDTO(entity);
    }

    public List<UserResponseDTO> convertUserListToUserResponseDTOList(List<User> users) {
        return users.stream().map(this::convertUserToUserResponseDTO).toList();
    }

    public Page<UserResponseDTO> convertUserPageToUserResponseDTOPage(Page<User> users) {
        return users.map(UserResponseDTO::new);
    }
}
