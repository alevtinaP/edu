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

        assertEquals(title, game.getTitle(),"Название игры не совпадает с переданным в конструктор" );
        assertEquals(minAge, game.getMinAge(), "Минимальный возраст не совпадает с переданным в конструктор");
        assertEquals(rentalCost, game.getRentalCost(), "Стоимость аренды не совпадает с переданной в конструктор" );
        assertFalse(game.isRented(), "Конструктор некорректно обработал признак аренды");
    }

    @Test
    void testConstructorValidParameters () {
        assertNotNull(game,"Игра не может быть null после инициализации" );
        assertEquals("Game1", game.getTitle(), "Название игры должно быть 'Game1'" );
        assertEquals(10,game.getMinAge(), "Минимальный возраст должен быть 10");
        assertEquals(100, game.getRentalCost(), "Стоимость аренды должна быть 100");
           }

    @Test
    void testConstructorThrowsExceptionNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame(null, 10, 100);
        },"Конструктор должен выбрасывать IllegalArgumentException, если title равен null");
    }

    @Test
    void testConstructorThrowsExceptionEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame("", 10, 100);
        }, "Конструктор должен выбрасывать IllegalArgumentException, если title пустой");
    }

    @Test
    void testConstructorThrowsExceptionNegativeMinAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame("Game1", -1, 100);
        }, "Конструктор должен выбрасывать IllegalArgumentException при отрицательном минимальном возрасте");
    }

    @Test
    void testConstructorThrowsExceptionNonPositiveRentalCost() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardGame("Game1", 10, 0);
        }, "Конструктор должен выбрасывать IllegalArgumentException, если стоимость аренды <= 0");
    }

    @Test
    void testCanBeRentedReturnsTrueForValidAge() {
        Assertions.assertTrue(game.canBeRentedBy(11), "Метод canBeRentedBy должен возвращать true, если возраст игрока выше минимального");
    }

    @Test
    void testCanBeRentedReturnsFalseForUnderage() {
        Assertions.assertFalse(game.canBeRentedBy(9), "Метод canBeRentedBy должен возвращать false, если возраст игрока ниже минимального");
    }

}
