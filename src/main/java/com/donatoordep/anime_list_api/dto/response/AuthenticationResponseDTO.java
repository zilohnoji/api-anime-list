package com.donatoordep.anime_list_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AuthenticationResponseDTO {

    private String login;
    @JsonProperty("expire_token")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private Date expireToken;
    private String issuer;
    private String token;

    private AuthenticationResponseDTO() {
    }

    public AuthenticationResponseDTO(String login, String issuer, Date expires, String token) {
        this.token = token;
        this.login = login;
        this.issuer = issuer;
        this.expireToken = expires;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getExpireToken() {
        return expireToken;
    }

    public void setExpireToken(Date expireToken) {
        this.expireToken = expireToken;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
