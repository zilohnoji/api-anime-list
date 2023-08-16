package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AnimeRepositoryTest {

    @Autowired
    AnimeRepository repository;

    Anime anime;

    @BeforeEach
    void setup() {
        anime = new Anime("Attack on titan", "descrição gigante", "https://imagem.com", "Pedro Donato",
                Status.COMPLETE, 250);
    }

    @Test
    @DisplayName("Given Anime Object When FindById Should Return Anime")
    void testGivenAnimeObject_When_FindById_ShouldReturn_AnimeObject() {

        repository.save(anime);

        // When / Act- Inicia a execução do cenário.
        Anime animeSearch = repository.findById(anime.getId()).get();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeSearch, () -> "Anime not should return null");
        assertEquals(anime.getId(), animeSearch.getId(), () -> "Anime should returned same id");
        assertTrue(animeSearch.getId() > 0, () -> "Anime id is not valid");
    }

    @Test
    @DisplayName("Given Anime Object When Delete Should Remove Anime")
    void testGivenAnimeObject_When_Delete_ShouldRemoveAnime() {

        repository.save(anime);

        // When / Act- Inicia a execução do cenário.
        repository.deleteById(anime.getId());

        Optional<Anime> animeAfterDelete = repository.findById(anime.getId());

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertTrue(animeAfterDelete.isEmpty(), () -> "Anime after delete should be null");
    }

    @Test
    @DisplayName("Given Anime List When FindAll Should Return List Anime")
    void testGivenAnimeList_When_FindAll_ShouldReturn_AnimeList() {
        // Given / Arrange - Cenário inicial das classes (setar configurações, iniciar variaveis)
        Anime animeTwo = new Anime();

        Anime animeSaved = repository.save(anime);
        Anime animeSavedTwo = repository.save(animeTwo);

        // When / Act- Inicia a execução do cenário.
        List<Anime> animeList = repository.findAll();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeList, () -> "Anime list not should return null");
        assertEquals(2, animeList.size(), () -> "Anime list should returned 2 of length");
    }

    @Test
    @DisplayName("Given Anime Object When Save Should Return Anime Saved")
    void testGivenAnimeObject_When_Save_ShouldReturn_AnimeSaved() {

        Anime animeSaved = repository.save(anime);

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeSaved, () -> "Anime not should return null");
        assertTrue(animeSaved.getId() > 0, () -> "Id can´t nullable");
    }

    // Nomeclatura test[System Under Test]_[Condition or State Change]_[Expected Result]
    @Test
    @DisplayName("Given Anime Object When FindByName Is Called Should Return Anime Object")
    void testGivenAnimeObject_When_FindByNameIsCalled_ShouldReturn_AnimeObject() {
        // Given / Arrange - Cenário inicial das classes (setar configurações, iniciar variaveis)
        repository.save(anime);

        // When / Act- Inicia a execução do cenário.
        List<Anime> output = repository.findByName("attack");

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(output, () -> "FindByName should return minimum one anime");
        assertEquals(anime.getTitle(), output.get(0).getTitle(), ()-> "Should returned same title");
        assertTrue(output.get(0).getId() > 0, () -> "Id can´t nullable");
    }
}
