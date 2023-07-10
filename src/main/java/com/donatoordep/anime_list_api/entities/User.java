package com.donatoordep.anime_list_api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String imgUrl;
    private String bio;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "stats_id")
    private AccountStats animeStats;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns =
    @JoinColumn(referencedColumnName = "id", name = "user_id"), inverseJoinColumns =
    @JoinColumn(referencedColumnName = "id", name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, String password, String imgUrl, String bio) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.imgUrl = imgUrl;
        this.bio = bio;
    }

    public void addAnime(AnimeOrder anime) {
        cart.getFavorites().add(anime);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public AccountStats getAnimeStats() {
        return animeStats;
    }

    public List<Role> getRoles() {
        return roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(imgUrl, user.imgUrl) && Objects.equals(bio, user.bio) && Objects.equals(cart, user.cart) && Objects.equals(animeStats, user.animeStats) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, imgUrl, bio, cart, animeStats, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", cart=" + cart +
                ", animeStats=" + animeStats +
                ", roles=" + roles +
                '}';
    }
}
