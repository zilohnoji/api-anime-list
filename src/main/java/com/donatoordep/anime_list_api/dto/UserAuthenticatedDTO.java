package com.donatoordep.anime_list_api.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAuthenticatedDTO {

    private Long id;
    private String name;
    private String email;
    private ProfileUserDTO profile;
    private CartDTO cart;
    private List<RoleDTO> roles = new ArrayList<>();

    public UserAuthenticatedDTO() {
    }

    public UserAuthenticatedDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileUserDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileUserDTO profile) {
        this.profile = profile;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }
}
