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
        assertTrue(game.isRented(), "После успешной аренды флаг isRented у игры должен стать true");
    }

    @Test
    void testRentGameThrowsExceptionIfAlreadyRented() {
        rental.rentGame("Имаджинариум", 10);

        assertThrows(IllegalStateException.class, () -> {
            rental.rentGame("Имаджинариум", 12);
        }, "Метод rentGame должен выбрасывать IllegalStateException при попытке арендовать уже занятую игру");
    }

    @Test
    void testRentGameThrowsExceptionIfNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            rental.rentGame("Такой игры нет", 10);
        }, "Метод rentGame должен выбрасывать IllegalArgumentException, если запрашиваемая игра отсутствует в каталоге");
    }

    @Test
    void testReturnGameSuccessfully() {
        rental.rentGame("Имаджинариум", 10);
        rental.returnGame("Имаджинариум");

        assertFalse(game.isRented(),
                "После успешного возврата игры флаг isRented должен измениться на false");
    }

    @Test
    void testReturnGameThrowsExceptionIfNotRented() {
        assertThrows(IllegalStateException.class, () -> {
            rental.returnGame("Имаджинариум");
        }, "Метод returnGame должен выбрасывать IllegalStateException, если игра еще не была арендована");
    }

    @Test
    void testReturnGameThrowsExceptionIfNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            rental.returnGame("Такой игры нет");
        }, "Метод returnGame должен выбрасывать IllegalArgumentException при попытке вернуть несуществующую игру");
    }

    @Test
    void testResetClearsRentedStatusForAllGames() {
        BoardGame secondGame = new BoardGame("UNO", 6, 150);
        rental.addGame(secondGame);

        rental.rentGame("Имаджинариум", 10);
        rental.rentGame("UNO", 6);

        assertTrue(game.isRented(), "Первая игра должна быть арендована перед сбросом");
        assertTrue(secondGame.isRented(), "Вторая игра должна быть арендована перед сбросом");

        rental.reset();

        assertFalse(game.isRented(), "После вызова reset флаг isRented первой игры должен стать false");
        assertFalse(secondGame.isRented(), "После вызова reset флаг isRented второй игры должен стать false");
    }
}

