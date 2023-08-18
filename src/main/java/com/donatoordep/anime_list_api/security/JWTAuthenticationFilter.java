package com.donatoordep.anime_list_api.security;

import com.donatoordep.anime_list_api.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenJWTService service;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenJWT = recoverToken(request);

        if (recoverToken(request) != null) {
            String login = service.validateToken(tokenJWT);// Obtém o email do usuário logado
            UserDetails user = repository.findByEmailForUserDetails(login); // Buscando o usuario que fez a requisição

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
        }
        corsConfigurer(response);
        filterChain.doFilter(request, response);
    }

    private void corsConfigurer(HttpServletResponse response) {
        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }

        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.addHeader("Access-Control-Allow-Headers", "*");
        }

        if (response.getHeader("Access-Control-Request-Headers") == null) {
            response.addHeader("Access-Control-Request-Headers", "*");
        }

        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }

    // Recuperar o token JWT do cabeçalho da requisição
    private String recoverToken(HttpServletRequest request) {
        return (request.getHeader("Authorization") != null)
                ? request.getHeader("Authorization").split(" ")[1] : null;
    }

}
