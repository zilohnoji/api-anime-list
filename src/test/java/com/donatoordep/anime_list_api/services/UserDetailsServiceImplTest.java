package com.donatoordep.anime_list_api.services;

import com.donatoordep.anime_list_api.entities.User;
import com.donatoordep.anime_list_api.repositories.UserRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserDetailsServiceImpl service;

    @Test
    @DisplayName("Given UserDetails Object When FindByEmailForUserDetails Is Called Should Return UserDetails")
    void testGiven_UserDetails_Object_When_FindByEmailForUserDetails_Is_Called_Should_Return_UserDetails() {

        User user = new User("Pedro", "pedro@gmail.com", "123456",
                "http://img.com", "Sou o Pedro");

        when(repository.findByEmailForUserDetails(user.getEmail())).thenReturn((UserDetails) user);
        UserDetails output = service.loadUserByUsername(user.getEmail());

        Assertions.assertEquals(user.getEmail(), output.getUsername(),
                () -> String.format("Expected: %s\nActual: %s", user.getEmail(), output.getUsername()));
    }
}
