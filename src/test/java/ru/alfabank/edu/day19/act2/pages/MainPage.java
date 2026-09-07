package ru.alfabank.edu.day19.act2.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement formAuthenticationLink = $x("//a[text()='Form Authentication']");

    public MainPage open(){
        Selenide.open("https://the-internet.herokuapp.com/");
        return this;
    }

    public LoginPage clickFormAuthentication() {
        formAuthenticationLink.click();
        return new LoginPage();
    }

}
