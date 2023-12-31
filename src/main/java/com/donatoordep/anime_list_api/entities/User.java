package com.donatoordep.anime_list_api.entities;

import com.donatoordep.anime_list_api.enums.StatusOrder;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 250)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private ProfileUser profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_roles", joinColumns =
    @JoinColumn(referencedColumnName = "id", name = "user_id"), inverseJoinColumns =
    @JoinColumn(referencedColumnName = "id", name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfileUser getProfile() {
        return profile;
    }

    public void setProfile(ProfileUser profile) {
        this.profile = profile;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void addAnime(AnimeOrder anime) {
        cart.getFavorites().add(anime);
        cart.setTotalAnimes(cart.getTotalAnimes() + 1);
        AccountStats accountStats = profile.getAnimeStats();
        if (is(anime, StatusOrder.COMPLETED)) {
            accountStats.setCompleted(accountStats.getCompleted() + 1);
        } else if (is(anime, StatusOrder.DROPPED)) {
            accountStats.setDropped(accountStats.getDropped() + 1);
        } else if (is(anime, StatusOrder.PLAN_TO_WATCH)) {
            accountStats.setPlanToWatch(accountStats.getPlanToWatch() + 1);
        } else if (is(anime, StatusOrder.WATCHING)) {
            accountStats.setWatching(accountStats.getWatching() + 1);
        }
    }

    public boolean is(AnimeOrder anime, StatusOrder status) {
        return anime.getAnimeOrderDetails().stream()
                .anyMatch(animeOrderDetails -> status
                        .equals(animeOrderDetails.getStatusOrder()));
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(cart, user.cart) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, cart, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cart=" + cart +
                ", roles=" + roles +
                '}';
    }
}
