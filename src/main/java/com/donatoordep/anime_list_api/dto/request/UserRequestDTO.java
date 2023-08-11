package com.donatoordep.anime_list_api.dto.request;

import jakarta.validation.constraints.*;

public class UserRequestDTO {

    @NotBlank(message = "The name is required")
    @Size(min = 5, max = 80, message = "Name size interval is (3 - 80)")
    private String name;
    @NotBlank(message = "The email is required")
    @Email(message = "The format of email is not valid")
    private String email;
    @NotBlank(message = "The password is required")
    @Size(min = 5, max = 80, message = "Password size interval is (10 - 80)")
    private String password;
    private ProfileUserRequestDTO profile;
    private String role;

    private UserRequestDTO() {
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

    public ProfileUserRequestDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileUserRequestDTO profile) {
        this.profile = profile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
