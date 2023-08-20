package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.builders.AnimeBuilder;
import com.donatoordep.anime_list_api.entities.Anime;
import com.donatoordep.anime_list_api.entities.AnimeOrderDetails;
import com.donatoordep.anime_list_api.enums.Status;
import com.donatoordep.anime_list_api.enums.StatusOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AnimeOrderDetailsRepositoryTest {

    @Autowired
    AnimeOrderDetailsRepository repository;

    @Autowired
    AnimeRepository repositoryAnime;

    AnimeOrderDetails animeOrderDetails;
    Anime anime;

    @BeforeEach
    void setup() {
        anime = AnimeBuilder.builder()
                .title("Attack on titan")
                .description("descrição gigante")
                .imgUrl("https://imagem.com")
                .authorName("Pedro Donato")
                .status(Status.COMPLETE)
                .episodes(250)
                .build();

        animeOrderDetails = new AnimeOrderDetails(anime, 110, StatusOrder.WATCHING);
    }

    @Test
    @DisplayName("Given AnimeOrderDetails Object When FindById Should Return AnimeOrderDetails")
    void testGivenAnimeOrderDetailsObject_When_FindById_ShouldReturn_AnimeOrderDetailsObject() {

        repository.save(animeOrderDetails);

        // When / Act- Inicia a execução do cenário.
        AnimeOrderDetails animeOrderDetailsSearch = repository.findById(animeOrderDetails.getId()).get();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeOrderDetailsSearch, () -> "AnimeOrderDetails not should return null");
        assertEquals(animeOrderDetails.getId(), animeOrderDetailsSearch.getId(), () -> "AnimeOrderDetails should returned same id");
        assertTrue(animeOrderDetailsSearch.getId() > 0, () -> "AnimeOrderDetails id is not valid");
    }

    @Test
    @DisplayName("Given AnimeOrderDetails Object When Delete Should Remove AnimeOrderDetails")
    void testGivenAnimeOrderDetailsObject_When_Delete_ShouldRemoveAnimeOrderDetails() {

        repository.save(animeOrderDetails);

        // When / Act- Inicia a execução do cenário.
        repository.deleteById(animeOrderDetails.getId());

        Optional<AnimeOrderDetails> animeOrderDetailsAfterDelete = repository.findById(animeOrderDetails.getId());

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertTrue(animeOrderDetailsAfterDelete.isEmpty(), () -> "AnimeOrderDetails after delete should be null");
    }

    @Test
    @DisplayName("Given AnimeOrderDetails List When FindAll Should Return List AnimeOrderDetails")
    void testGivenAnimeOrderDetailsList_When_FindAll_ShouldReturn_AnimeOrderDetailsList() {
        // Given / Arrange - Cenário inicial das classes (setar configurações, iniciar variaveis)

        repositoryAnime.save(anime);
        repository.save(animeOrderDetails);

        // When / Act- Inicia a execução do cenário.
        List<AnimeOrderDetails> animeOrderDetailsList = repository.findAll();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeOrderDetailsList, () -> "AnimeOrderDetails list not should return null");
        assertEquals(1, animeOrderDetailsList.size(), () -> "AnimeOrderDetails list should returned 2 of length");
        assertEquals(animeOrderDetailsList.get(0).getAnime().getTitle(), anime.getTitle(),
                () -> "The title should return same title");
    }

    @Test
    @DisplayName("Given AnimeOrderDetails Object When Save Should Return AnimeOrderDetails Saved")
    void testGivenAnimeOrderDetailsObject_When_Save_ShouldReturn_AnimeOrderDetailsSaved() {

        AnimeOrderDetails animeOrderDetailsSaved = repository.save(animeOrderDetails);

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeOrderDetailsSaved, () -> "AnimeOrderDetails not should return null");
        assertTrue(animeOrderDetailsSaved.getId() > 0, () -> "Id can´t nullable");
    }
}
