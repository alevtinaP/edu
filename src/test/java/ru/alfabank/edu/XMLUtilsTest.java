package ru.alfabank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.alfabank.edu.day14.XMLUtils;

public class XMLUtilsTest {

    @Test
    public void validTagName() {
        //Arrange (данные)
        String inputTagName = "user";
        String expectedOutput = "<user></user>";

        //Act
        String actualOutput = XMLUtils.createEmptyElement(inputTagName);

        //Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "Метод должен возвращать корректные теги для валидного имени " + expectedOutput + " для значения " + inputTagName);

    }
    @Test
    public void invalidTagName_Null() {
        //Arrange (данные)
        String inputTagName = null;
        String expectedOutput = "<invalid/>";

        //Act
        String actualOutput = XMLUtils.createEmptyElement(inputTagName);

        //Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "Метод должен возвращать  " + expectedOutput + " если передан null " );
    }

    @Test
    public void invalidTagName_EmptyString() {
        //Arrange (данные)
        String inputTagName = "";
        String expectedOutput = "<invalid/>";

        //Act
        String actualOutput = XMLUtils.createEmptyElement(inputTagName);

        //Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "Метод должен возвращать  " + expectedOutput + " если передана пустая строка" );
    }

}
