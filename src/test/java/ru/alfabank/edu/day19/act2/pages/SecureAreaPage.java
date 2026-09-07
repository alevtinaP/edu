package ru.alfabank.edu.day19.act2.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SecureAreaPage {

    private final SelenideElement flashMessage = $x("//div[@id='flash']");
    private final SelenideElement logoutButton = $x("//a[contains(., 'Logout')]");

    public SecureAreaPage checkFlashMessageContains(String expectedText) {
        flashMessage.shouldHave(Condition.text(expectedText));
        return this;
    }

    public SecureAreaPage checkLogoutButtonVisible() {
        logoutButton.shouldBe(Condition.visible);
        return this;
    }

    public LoginPage clickLogout() {
        logoutButton.click();
        return new LoginPage();
    }

}
