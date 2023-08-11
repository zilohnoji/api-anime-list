package com.donatoordep.anime_list_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthenticationRequestDTO {
    @NotBlank(message = "The email is required")
    @Email(message = "The format of email is not valid")
    private String email;
    @NotBlank(message = "The password is required")
    @Size(min = 5, max = 80, message = "Password size interval is (10 - 80)")
    private String password;

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
}
