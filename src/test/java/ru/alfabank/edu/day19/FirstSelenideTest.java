package ru.alfabank.edu.day19;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;

public class FirstSelenideTest {


    @Test
    public void testSuccessfulLogin() {

        //1. Открыть страницу https://the-internet.herokuapp.com
        String url = "https://the-internet.herokuapp.com/";
        Selenide.open(url);

        //2. Кликнуть по ссылке с текстом Form Authentication
        SelenideElement link = $x("//a[text()='Form Authentication']");
        link.click();

        //3. Проверить что заголовок страницы содержит текст Login Page
        SelenideElement titleLoginPage = $x("//h2").shouldHave(Condition.text("Login Page"));

        //4. Установить в Username значение tomsmith
        SelenideElement inputUserName = $x("//input[@id='username']").setValue("tomsmith");

        //5. Установить в поле Password значение SuperSecretPassword!
        SelenideElement inputPassword = $x("//input[@id='password']").setValue("SuperSecretPassword!");

        //6. Нажать кнопку Login
        SelenideElement buttonLogin = $x("//button[@type= 'submit']");
        buttonLogin.click();

        //7. Найти сообщение результата логина и проверить, что оно содержит текст You logged into a secure area!
        SelenideElement massage = $x("//div[@id='flash']").shouldHave(Condition.text("You logged into a secure area!"));

        //8. Проверить что на экране присутствует кнокпа Logout
        SelenideElement buttonLogout = $x("//a[contains(., 'Logout')]").shouldBe(Condition.visible);

        //9. Нажать на кнопку Logout
        buttonLogout.click();

        //10. Проверить что страница на которую произошел переход - имеет заголовок с текстом Login Page
        $x("//h2").shouldHave(Condition.text("Login Page"));
    }

    @Test
    public void testFailedLogin() {
        //1. Открыть страницу https://the-internet.herokuapp.com/
        String url = "https://the-internet.herokuapp.com/";
        Selenide.open(url);

        //2. Кликнуть по ссылке с текстом Form Authentication
        SelenideElement link = $x("//a[text()='Form Authentication']");
        link.click();

        //3. Проверить что внизу страницы есть ссылка с текстом Elemental Selenium
        SelenideElement linkElementSelenium = $x("//a[text()='Elemental Selenium']").shouldHave(Condition.text("Elemental Selenium"));

        //4. Установить в Username значение admin
        SelenideElement inputUserName = $x("//input[@id='username']").setValue("admin");

        //5. Установить в поле Password значение 1234
        SelenideElement inputPassword = $x("//input[@id='password']").setValue("1234");


        //6. Нажать кнопку Login
        SelenideElement buttonLogin = $x("//button[@type= 'submit']");
        buttonLogin.click();

        //7. Найти сообщение результата логина и проверить, что оно содержит текст Your username is invalid!
        SelenideElement massage = $x("//div[@id='flash']").shouldHave(Condition.text("Your username is invalid!"));
    }
}
