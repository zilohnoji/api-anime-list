package com.donatoordep.anime_list_api.builders.dto.response;

import com.donatoordep.anime_list_api.dto.response.AuthenticationResponseDTO;

import java.util.Date;

public class AuthenticationResponseDTOBuilder {

    private AuthenticationResponseDTO authenticationResponseDTO;

    private AuthenticationResponseDTOBuilder() {
        this.authenticationResponseDTO = new AuthenticationResponseDTO();
    }

    public static AuthenticationResponseDTOBuilder builder() {
        return new AuthenticationResponseDTOBuilder();
    }

    public AuthenticationResponseDTO build() {
        return authenticationResponseDTO;
    }

    public AuthenticationResponseDTOBuilder login(String login) {
        this.authenticationResponseDTO.setLogin(login);
        return this;
    }

    public AuthenticationResponseDTOBuilder expireToken(Date expireToken) {
        this.authenticationResponseDTO.setExpireToken(expireToken);
        return this;
    }

    public AuthenticationResponseDTOBuilder issuer(String issuer) {
        this.authenticationResponseDTO.setIssuer(issuer);
        return this;
    }

    public AuthenticationResponseDTOBuilder token(String token) {
        this.authenticationResponseDTO.setToken(token);
        return this;
    }
}
