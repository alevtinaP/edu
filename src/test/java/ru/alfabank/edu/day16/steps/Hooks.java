package ru.alfabank.edu.day16.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {


    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println(">>>> Старт сценария: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        String status = scenario.isFailed() ? "FAILED" : "PASSED";
        System.out.println("<<< Сценарий " + scenario.getName() + " завершен со статустом: " + status);
    }
}
