package ru.alfabank.edu.day15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameRentalTest {
    private GameRental rental;
    private BoardGame game;

    @BeforeEach
    void setUp() {
        rental = new GameRental();
        game = new BoardGame("Имаджинариум", 10, 500);
        rental.addGame(game);
    }

    @Test
    void testRentGameSuccessfully() {
        rental.rentGame("Имаджинариум", 10);
        assertTrue(game.isRented());
    }

    @Test
    void testRentGameThrowsExceptionIfAlreadyRented() {
        rental.rentGame("Имаджинариум", 10);

        assertThrows(IllegalStateException.class, () -> {
            rental.rentGame("Имаджинариум", 12);
        });
    }

    @Test
    void testRentGameThrowsExceptionIfNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            rental.rentGame("Такой игры нет", 10);
        });
    }


    @Test
    void testReturnGameSuccessfully() {
        rental.rentGame("Имаджинариум", 10);
        rental.returnGame("Имаджинариум");

        assertFalse(game.isRented());
    }

    @Test
    void testReturnGameThrowsExceptionIfNotRented() {
        assertThrows(IllegalStateException.class, () -> {
            rental.returnGame("Имаджинариум");
        });
    }

    @Test
    void testReturnGameThrowsExceptionIfNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            rental.returnGame("Такой игры нет");
        });
    }
}

