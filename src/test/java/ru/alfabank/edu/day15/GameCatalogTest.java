package ru.alfabank.edu.day15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCatalogTest {
    private GameRental rental;

    @BeforeEach
    void setUp() {
        rental = new GameRental();
    }

    @Test
    void testAddGameSuccessful() {
        BoardGame boardGame = new BoardGame("Спящая королева", 10, 100);
        rental.addGame(boardGame);
        assertEquals(boardGame, rental.getBoardGameCatalog().getLast(),
                "Метод добавления игры в каталог не работает");
    }

    @Test
    void testAddGameThrowsExceptionOnNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            rental.addGame(null);
        }, "Метод addGame должен выбрасывать IllegalArgumentException при попытке добавить null");
    }

    @Test
    void testAddGameThrowsExceptionOnDuplicateTitle() {
        BoardGame game1 = new BoardGame("Игра", 10, 100);
        rental.addGame(game1);

        assertThrows(IllegalArgumentException.class, () -> {
            rental.addGame(new BoardGame("Игра", 12, 150));
        }, "Метод addGame должен выбрасывать IllegalArgumentException при добавлении игры с уже существующим названием");
    }

    @Test
    void testSearchGameReturnsNullIfNotFound() {
        assertNull(rental.searchGame("Несуществующя игра"),
                "Метод searchGame должен возвращать null, если игра с таким названием отсутствует в каталоге");
    }

    @Test
    void testRentGameThrowsExceptionIfNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            rental.rentGame("Несуществующая игра", 10);
        }, "Метод rentGame должен выбрасывать IllegalArgumentException, если запрашиваемой игры нет в каталоге");
    }
}
