package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.AnimeOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AnimeOrderRepositoryTest {

    @Autowired
    AnimeOrderRepository repository;

    AnimeOrder animeOrder;

    @BeforeEach
    void setup() {
        animeOrder = new AnimeOrder();
    }

    @Test
    @DisplayName("Given AnimeOrder Object When FindById Should Return AnimeOrder")
    void testGivenAnimeOrderObject_When_FindById_ShouldReturn_AnimeOrderObject() {

        repository.save(animeOrder);

        // When / Act- Inicia a execução do cenário.
        AnimeOrder animeOrderSearch = repository.findById(animeOrder.getId()).get();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeOrderSearch, () -> "AnimeOrder not should return null");
        assertEquals(animeOrder.getId(), animeOrderSearch.getId(), () -> "AnimeOrder should returned same id");
        assertTrue(animeOrderSearch.getId() > 0, () -> "AnimeOrder id is not valid");
    }

    @Test
    @DisplayName("Given AnimeOrder Object When Delete Should Remove AnimeOrder")
    void testGivenAnimeOrderObject_When_Delete_ShouldRemoveAnimeOrder() {

        repository.save(animeOrder);

        // When / Act- Inicia a execução do cenário.
        repository.deleteById(animeOrder.getId());

        Optional<AnimeOrder> animeOrderAfterDelete = repository.findById(animeOrder.getId());

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertTrue(animeOrderAfterDelete.isEmpty(), () -> "AnimeOrder after delete should be null");
    }

    @Test
    @DisplayName("Given AnimeOrder List When FindAll Should Return List AnimeOrder")
    void testGivenAnimeOrderList_When_FindAll_ShouldReturn_AnimeOrderList() {
        // Given / Arrange - Cenário inicial das classes (setar configurações, iniciar variaveis)
        AnimeOrder animeOrderTwo = new AnimeOrder();

        AnimeOrder animeOrderSaved = repository.save(animeOrder);
        AnimeOrder animeOrderSavedTwo = repository.save(animeOrderTwo);

        // When / Act- Inicia a execução do cenário.
        List<AnimeOrder> animeOrderList = repository.findAll();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeOrderList, () -> "AnimeOrder list not should return null");
        assertEquals(2, animeOrderList.size(), () -> "AnimeOrder list should returned 2 of length");
    }

    @Test
    @DisplayName("Given AnimeOrder Object When Save Should Return AnimeOrder Saved")
    void testGivenAnimeOrderObject_When_Save_ShouldReturn_AnimeOrderSaved() {

        AnimeOrder animeOrderSaved = repository.save(animeOrder);

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(animeOrderSaved, () -> "AnimeOrder not should return null");
        assertTrue(animeOrderSaved.getId() > 0, () -> "Id can´t nullable");
    }
}
