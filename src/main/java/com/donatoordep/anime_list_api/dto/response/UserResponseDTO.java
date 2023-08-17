package com.donatoordep.anime_list_api.dto.response;

import com.donatoordep.anime_list_api.dto.RoleDTO;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private ProfileUserResponseDTO profile;
    private CartResponseDTO cart;
    private List<RoleDTO> roles = new ArrayList<>();

    public UserResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
