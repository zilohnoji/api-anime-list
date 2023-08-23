package com.donatoordep.anime_list_api.repositories;

import com.donatoordep.anime_list_api.entities.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    CartRepository repository;

    Cart cart;

    @BeforeEach
    void setup() {
        cart = new Cart();
    }

    @Test
    @DisplayName("Given Cart Object When FindById Should Return Cart")
    void testGivenCartObject_When_FindById_ShouldReturn_CartObject() {

        repository.save(cart);

        // When / Act- Inicia a execução do cenário.
        Cart cartSearch = repository.findById(cart.getId()).get();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(cartSearch, () -> "Cart not should return null");
        assertEquals(cart.getId(), cartSearch.getId(), () -> "Cart should returned same id");
        assertTrue(cartSearch.getId() > 0, () -> "Cart id is not valid");
    }

    @Test
    @DisplayName("Given Cart Object When Delete Should Remove Cart")
    void testGivenCartObject_When_Delete_ShouldRemoveCart() {

        repository.save(cart);

        // When / Act- Inicia a execução do cenário.
        repository.deleteById(cart.getId());

        Optional<Cart> cartAfterDelete = repository.findById(cart.getId());

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertTrue(cartAfterDelete.isEmpty(), () -> "Cart after delete should be null");
    }

    @Test
    @DisplayName("Given Cart List When FindAll Should Return List Cart")
    void testGivenCartList_When_FindAll_ShouldReturn_CartList() {

        // When / Act- Inicia a execução do cenário.
        List<Cart> cartList = repository.findAll();

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(cartList, () -> "Cart list not should return null");
        assertEquals(2, cartList.size(), () -> "Cart list should returned 2 of length");
    }

    @Test
    @DisplayName("Given Cart Object When Save Should Return Cart Saved")
    void testGivenCartObject_When_Save_ShouldReturn_CartSaved() {

        Cart cartSaved = repository.save(cart);

        // Then / Assert - Avaliação do resultado, verifica se corresponde ao esperado.
        assertNotNull(cartSaved, () -> "Cart not should return null");
        assertTrue(cartSaved.getId() > 0, () -> "Id can´t nullable");
    }
}
