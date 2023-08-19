package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.AccountStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AccountStatsRepositoryTest {

    @Autowired
    AccountStatsRepository repository;

    AccountStats accountStats;

    @BeforeEach
    void setup() {
        accountStats = new AccountStats();
    }

    @Test
    @DisplayName("Given AccountStats Object When FindById Should Return AccountStats")
    void testGivenAccountStatsObject_When_FindById_ShouldReturn_AccountStatsObject() {

        repository.save(accountStats);

        // When / Act- Inicia a execução do cenário.
        AccountStats accountStatsSearch = repository.findById(accountStats.getId()).get();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertTrue(accountStatsSearch.getId() > 0, () -> "AccountStats id is not valid");
        assertNotNull(accountStatsSearch, () -> "AccountStats not should return null");
        assertEquals(accountStats.getId(), accountStatsSearch.getId(),
                () -> String.format(
                        "Expected: %s\nActual: %s", accountStats.getId(), accountStatsSearch.getId()));
    }

    @Test
    @DisplayName("Given AccountStats Object When Delete Should Remove AccountStats")
    void testGivenAccountStatsObject_When_Delete_ShouldRemoveAccountStats() {

        repository.save(accountStats);

        // When / Act- Inicia a execução do cenário.
        repository.deleteById(accountStats.getId());

        Optional<AccountStats> accountStatsAfterDelete = repository.findById(accountStats.getId());

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertTrue(accountStatsAfterDelete.isEmpty(), () -> "AccountStats after delete should be null");
    }

    @Test
    @DisplayName("Given AccountStats List When FindAll Should Return List AccountStats")
    void testGivenAccountStatsList_When_FindAll_ShouldReturn_AccountStatsList() {
        // Given / Arrange - Cenário inicial das classes (setar configurações, iniciar variaveis)
        AccountStats accountStatsTwo = new AccountStats();

        AccountStats accountStatsSaved = repository.save(accountStats);
        AccountStats accountStatsSavedTwo = repository.save(accountStatsTwo);

        // When / Act- Inicia a execução do cenário.
        List<AccountStats> accountStatsList = repository.findAll();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(accountStatsList, () -> "AccountStats list not should return null");
        assertEquals(2, accountStatsList.size(),
                () -> String.format("Expected: %d\nActual: %d", 2, accountStatsList.size()));
    }

    @Test
    @DisplayName("Given AccountStats Object When Save Should Return AccountStats Saved")
    void testGivenAccountStatsObject_When_Save_ShouldReturn_AccountStatsSaved() {

        AccountStats accountStatsSaved = repository.save(accountStats);

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(accountStatsSaved, () -> "AccountStats not should return null");
        assertTrue(accountStatsSaved.getId() > 0, () -> "Id can´t nullable");
    }
}
