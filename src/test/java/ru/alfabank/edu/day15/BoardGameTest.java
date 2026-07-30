package ru.alfabank.edu.day15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardGameTest {
    private BoardGame game;

    @BeforeEach
    void setUp() {
        game = new BoardGame("Game1", 10, 100);
    }


    @ParameterizedTest
    @CsvSource({
            "Имаджинариум, 10, 100",
            "UNO, 12, 150"
    })
    void testConstructorWithValidParameters(String title, int minAge, int rentalCost) {
        BoardGame game = new BoardGame(title, minAge, rentalCost);

        assertEquals(title, game.getTitle());
        assertEquals(minAge, game.getMinAge());
        assertEquals(rentalCost, game.getRentalCost());
        assertFalse(game.isRented());
    }

    @Test
    void testConstructorValidParameters () {
        assertNotNull(game);
        assertEquals("Game1", game.getTitle());
        assertEquals(10,game.getMinAge());
        assertEquals(100, game.getRentalCost());
     //   Assertions.assertEquals(game.isRented());
    }

    @Test
    void testConstructorThrowsExceptionNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame(null, 10, 100);
        });
    }

    @Test
    void testConstructorThrowsExceptionEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame("", 10, 100);
        });
    }

    @Test
    void testConstructorThrowsExceptionNegativeMinAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame("Game1", -1, 100);
        });
    }

    @Test
    void testConstructorThrowsExceptionNonPositiveRentalCost() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame("Game1", 10, 0);
        });
    }

    @Test
    void testCanBeRentedReturnsTrueForValidAge() {
        Assertions.assertTrue(game.canBeRentedBy(11));
    }

    @Test
    void testCanBeRentedReturnsFalseForUnderage() {
        Assertions.assertFalse(game.canBeRentedBy(9));
    }

}
