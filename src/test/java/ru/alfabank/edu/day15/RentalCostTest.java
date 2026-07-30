package ru.alfabank.edu.day15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class RentalCostTest {
    private GameRental rental;
    private BoardGame game;

    @BeforeEach
    void setUp() {
        rental = new GameRental();
        game = new BoardGame("Имаджинариум", 10, 500);
        rental.addGame(game);
    }

    @Test
    void testCalculateCostSuccessfully() {
        int rentalDays = 5;
        int expectedCost = rental.searchGame("Имаджинариум").getRentalCost() * rentalDays;
        assertEquals(expectedCost, rental.calculateCost("Имаджинариум", rentalDays),
                "Стоимость аренды должна быть равна: дни * стоимость игры");
    }

    @Test
    void testCalculateCostGameNotFoundThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rental.calculateCost("Монополия", 3);
        });
        assertEquals("Игра отсутствует в каталоге", exception.getMessage());
    }

}
