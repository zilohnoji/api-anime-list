package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.dto.RoleDTO;
import com.donatoordep.anime_list_api.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private ProfileUserResponseDTO profile;
    private CartResponseDTO cart;
    private List<RoleDTO> roles = new ArrayList<>();

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public UserResponseDTO() {
    }

    public void addRole(RoleDTO role) {
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileUserResponseDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileUserResponseDTO profile) {
        this.profile = profile;
    }

    public CartResponseDTO getCart() {
        return cart;
    }

    public void setCart(CartResponseDTO cart) {
        this.cart = cart;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }
}
