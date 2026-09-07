package ru.alfabank.edu.day19.act2.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement header = $x("//h2");
    private final SelenideElement usernameInput = $x("//input[@id='username']");
    private final SelenideElement passwordInput = $x("//input[@id='password']");
    private final SelenideElement loginButton = $x("//button[@type='submit']");
    private final SelenideElement flashMessage = $x("//div[@id='flash']");
    private final SelenideElement elementalSeleniumLink = $x("//a[text()='Elemental Selenium']");

    public LoginPage chekHeader(String expectedText) {
        header.shouldHave(Condition.text(expectedText));
        return this;
    }

    public LoginPage chekElementalSeleniumLink() {
        elementalSeleniumLink.shouldBe(Condition.visible);
        return this;

    }

    public LoginPage loginWithInvalidCredits(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
        return this;
    }

    public SecureAreaPage loginWithValidCredits(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
        return new SecureAreaPage();

    }

    public LoginPage checkFlashMessageContains(String expectedText) {
        flashMessage.shouldHave(Condition.text(expectedText));
        return this;
    }
}
