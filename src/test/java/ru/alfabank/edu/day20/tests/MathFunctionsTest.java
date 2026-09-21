package ru.alfabank.edu.day20.tests;


import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import ru.alfabank.edu.day20.steps.CalculatorSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Epic("Калькулятор")
@Feature("Математические операции")

public class MathFunctionsTest {
    private final CalculatorSteps steps = new CalculatorSteps();

    @Test
    @Story("Возведение в степень")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Petrova")
    @Description("Проверяем возведение в степень: 2^10 = 1024")
       public void testPower() {
        double result = steps.power(2, 10);
        steps.verifyResult(1024, result);
        assertEquals(1024, result);
    }

    @Test
    @Story("Квадратный корень")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Petrova")
    @Description("Проверяем квадратный корень из положительного числа: sqrt(81) = 9")
    @Link(value = "RKK-101", name = "Task: SGRT")
    public void testSqrtPositive() {
        double result = steps.sqrt(81);
        steps.verifyResult(9, result);
        assertEquals(9, result);
    }

    @Test
    @Story("Квадратный корень")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Petrova")
    @Description("Проверяем квадратный корень из отрицательного числа: ожидается ArithmeticException")
     public void testSqrtNegative() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            steps.sqrt(-9);
        });
        Allure.parameter("Ожидаемое исключение", "ArithmeticException");
        assertEquals("Корень из отрицательного числа", exception.getMessage());
    }


}
