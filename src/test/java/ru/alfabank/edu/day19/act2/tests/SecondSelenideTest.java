package ru.alfabank.edu.day19.act2.tests;

import org.junit.jupiter.api.Test;
import ru.alfabank.edu.day19.act2.pages.MainPage;

public class SecondSelenideTest {

    @Test
    public void testSuccessfulLogin() {
        new MainPage()
                .open()
                .clickFormAuthentication()
                .chekHeader("Login Page")
                .loginWithValidCredits("tomsmith", "SuperSecretPassword!")
                .checkFlashMessageContains("You logged into a secure area!")
                .checkLogoutButtonVisible()
                .clickLogout()
                .chekHeader("Login Page");
    }

    @Test
    public void testFailedLogin() {
        new MainPage()
                .open()
                .clickFormAuthentication()
                .loginWithInvalidCredits("admin", "1234")
                .checkFlashMessageContains("Your username is invalid!");
    }

}
