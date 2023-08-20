package com.donatoordep.anime_list_api.builders;

import com.donatoordep.anime_list_api.entities.Cart;
import com.donatoordep.anime_list_api.entities.ProfileUser;
import com.donatoordep.anime_list_api.entities.Role;
import com.donatoordep.anime_list_api.entities.User;

import java.util.List;

public class UserBuilder {

    private User user;

    private UserBuilder() {
        user = new User();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public User build() {
        return this.user;
    }

    public UserBuilder password(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder id(Long id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder profile(String imgUrl, String bio) {
        this.user.setProfile(new ProfileUser(imgUrl, bio));
        return this;
    }

    public UserBuilder profile(ProfileUser profileUser) {
        this.user.setProfile(profileUser);
        return this;
    }

    public UserBuilder cart() {
        this.user.setCart(new Cart());
        return this;
    }

    public UserBuilder cart(Cart cart) {
        this.user.setCart(cart);
        return this;
    }

    public UserBuilder name(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder email(String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder roles(List<Role> roles){
        this.user.setRoles(roles);
        return this;
    }
}
