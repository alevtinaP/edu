package ru.alfabank.edu.day20.tests;


import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import ru.alfabank.edu.day20.steps.CalculatorSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Epic("Калькулятор")
@Feature("Арифметические операции")

public class ArithmeticTest {
    private final CalculatorSteps steps = new CalculatorSteps();

    @Test
    @Story("Сложение")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Petrova")
    @Description("Проверяем базовое сложение двух положительных чисел")
    public void testAddPositiveNumbers() {
        double result = steps.add(2, 3);
        steps.verifyResult(result, 5);
    }

    @Test
    @Story("Сложение")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Petrova")
    @Description("Проверяем сложение с отрицательным числом: -5 + 3 = -2")
    @Link(value = "RKK-100")
    public void testAddWithNegativeNumber() {
        double result = steps.add(-5, 3);
        steps.verifyResult(-2, result);
        assertEquals(2, result);
    }

    @Test
    @Story("Вычитание")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Petrova")
    @Description("Проверяем вычитание: 10 - 4 = 6")
      public void testSubtract() {
        double result = steps.subtract(10, 4);
        steps.verifyResult(6, result);
        assertEquals(6, result);
    }

    @Test
    @Story("Умножение")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Petrova")
    @Description("Проверяем умножение: 7 * 8 = 56")
    public void testMultiply() {
        double result = steps.multiply(7, 8);
        steps.verifyResult(56, result);
        assertEquals(56, result);
    }

    @Test
    @Story("Деление")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Petrova")
    @Description("Проверяем деление: 15 / 3 = 5")
    @Link(value = "JIRA-1003", name = "Task: Division")
    public void testDivide() {
        double result = steps.divide(15, 3);
        steps.verifyResult(5, result);
        assertEquals(5, result);
    }

    @Test
    @Story("Деление")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Petrova")
    @Description("Проверяем деление на ноль: ожидается исключение")
    public void testDivideByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            steps.divide(10, 0);
        });
        Allure.parameter("Ожидаемое исключение", "ArithmeticException");
        assertEquals("Деление на ноль", exception.getMessage());
    }
}
