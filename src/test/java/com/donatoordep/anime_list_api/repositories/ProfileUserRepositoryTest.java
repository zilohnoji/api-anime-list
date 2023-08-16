package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.ProfileUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProfileUserRepositoryTest {

    @Autowired
    ProfileUserRepository repository;

    ProfileUser profile;

    @BeforeEach
    void setup() {
        profile = new ProfileUser("https://img_url.com", "My new bio");
    }

    @Test
    @DisplayName("Given ProfileUser Object When Save Should Return ProfileUser Saved")
    void testGivenProfileUserObject_When_Save_ShouldReturn_ProfileUserSaved() {

        ProfileUser profileSaved = repository.save(profile);

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(profileSaved, () -> "ProfileUser not should return null");
        assertTrue(profileSaved.getId() > 0, () -> "Id can´t nullable");
    }

    @Test
    @DisplayName("Given ProfileUser List When FindAll Should Return List ProfileUser")
    void testGivenProfileUserList_When_FindAll_ShouldReturn_ProfileUserList() {
        // Given / Arrange - Cenário inicial das classes (setar configurações, iniciar variaveis)
        ProfileUser profile = new ProfileUser("https://img_url1.com", "My new bio 1");
        ProfileUser profileTwo = new ProfileUser("https://img_url2.com", "My new bio 2");

        repository.save(profile);
        repository.save(profileTwo);

        // When / Act- Inicia a execução do cenário.
        List<ProfileUser> profileList = repository.findAll();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(profileList, () -> "ProfileUser list not should return null");
        assertEquals(2, profileList.size(), () -> "ProfileUser list should returned 2 of length");
    }

    @Test
    @DisplayName("Given ProfileUser Object When FindById Should Return ProfileUser")
    void testGivenProfileUserObject_When_FindById_ShouldReturn_ProfileUserObject() {

        repository.save(profile);

        // When / Act- Inicia a execução do cenário.
        ProfileUser profileSearch = repository.findById(profile.getId()).get();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(profileSearch, () -> "ProfileUser not should return null");
        assertEquals(profile.getId(), profileSearch.getId(), () -> "ProfileUser should returned same id");
        assertTrue(profileSearch.getId() > 0, () -> "ProfileUser id is not valid");
    }

    @Test
    @DisplayName("Given ProfileUser Object When Delete Should Remove ProfileUser")
    void testGivenProfileUserObject_When_Delete_ShouldRemoveProfileUser() {

        repository.save(profile);

        // When / Act- Inicia a execução do cenário.
        repository.deleteById(profile.getId());

        Optional<ProfileUser> profileAfterDelete = repository.findById(profile.getId());

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertTrue(profileAfterDelete.isEmpty(), () -> "ProfileUser after delete should be null");
    }
}
