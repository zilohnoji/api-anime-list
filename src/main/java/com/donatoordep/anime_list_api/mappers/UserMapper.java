package com.donatoordep.anime_list_api.mappers;

import com.donatoordep.anime_list_api.dto.*;
import com.donatoordep.anime_list_api.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default User toEntity(UserDTO dto) {
        User user = new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword());

        ProfileUser profileUser = new ProfileUser(dto.getProfile().getId(), dto.getProfile().getImgUrl(),
                dto.getProfile().getBio());

        profileUser.setAnimeStats(new AccountStats(dto.getProfile().getAccountStats().getId(),
                dto.getProfile().getAccountStats().getWatching(), dto.getProfile().getAccountStats().getCompleted(),
                dto.getProfile().getAccountStats().getDropped(), dto.getProfile().getAccountStats().getPlanToWatch()));

        user.setProfile(profileUser);

        List<AnimeOrderDetails> animeOrderDetails = new ArrayList<>();
        dto.getCart().getFavorites().forEach(animeOrderDTO -> animeOrderDTO.getAnimeOrderDetails()
                .forEach(animeOrderDetailsDTO -> animeOrderDetails.add(
                        new AnimeOrderDetails(animeOrderDetailsDTO.getId(),
                                new Anime(animeOrderDetailsDTO.getAnime().getTitle(),
                                        animeOrderDetailsDTO.getAnime().getDescription(),
                                        animeOrderDetailsDTO.getAnime().getImgUrl(),
                                        animeOrderDetailsDTO.getAnime().getAuthorName(),
                                        animeOrderDetailsDTO.getAnime().getStatus(),
                                        animeOrderDetailsDTO.getAnime().getEpisodes()),
                                        animeOrderDetailsDTO.getEpisode(),
                                        animeOrderDetailsDTO.getStatusOrder()))));

        List<AnimeOrder> animeOrderList = dto.getCart().getFavorites().stream().map(
                animeOrderDTO -> new AnimeOrder(animeOrderDetails)).toList();

        user.setCart(new Cart(dto.getCart().getId(), animeOrderList, dto.getCart().getTotalAnimes()));
        dto.getRoles().forEach(roleDTO -> new Role(roleDTO.getId(), roleDTO.getRoleName()));

        return user;
    }

    default UserDTO toDto(User entity) {

        UserDTO dto = new UserDTO(entity.getId(), entity.getName(),
                entity.getEmail(), entity.getPassword());

        ProfileUserDTO profileUserDTO = new ProfileUserDTO(entity.getProfile());
        profileUserDTO.setAccountStats(new AccountStatsDTO(entity.getProfile().getAnimeStats()));

        dto.setProfile(profileUserDTO);
        dto.setCart(new CartDTO(entity.getCart()));

        entity.getRoles().forEach(role -> dto.addRole(new RoleDTO(role)));

        return dto;
    }
}