package ru.alfabank.edu.day15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

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
        }, "Метод calculateCost должен выбрасывать IllegalArgumentException, если игры нет в каталоге");
        assertEquals("Игра отсутствует в каталоге", exception.getMessage());
    }


    @ParameterizedTest(name = "Игра: {0}, Дней: {1}, Стоимость за день: {2} -> Ожидаемая стоимость: {3}")
    @MethodSource("provideCostCalculationData")
    void testCalculateCostParameterized(String title, int rentalDays, int costPerDay, int expectedCost) {

        BoardGame dynamicGame = new BoardGame(title, 10, costPerDay);
        rental.addGame(dynamicGame);

        int actualCost = rental.calculateCost(title, rentalDays);

        assertEquals(expectedCost, actualCost,
                String.format("Ошибка расчета стоимости для игры '%s' на %d дн. при цене %d за день",
                        title, rentalDays, costPerDay));
    }


    private static Stream<Arguments> provideCostCalculationData() {
        return Stream.of(
                Arguments.of("Шахматы", 1, 300, 300),   // Краткосрочная аренда
                Arguments.of("Монополия", 3, 400, 1200), // Обычная аренда на несколько дней
                Arguments.of("Мафия", 7, 600, 4200),     // Длительная аренда
                Arguments.of("Каркассон", 10, 250, 2500) // Круглое количество дней
        );

    }
}