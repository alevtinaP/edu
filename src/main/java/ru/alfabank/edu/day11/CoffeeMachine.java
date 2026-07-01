package ru.alfabank.edu.day11;

public class CoffeeMachine {

    // метод приготовления кофе
    public void makeCoffee(int amountWater) {
        if (amountWater <= 200) {
            throw new NotEnoughWaterException("Закончилась вода, долейте воду в резервуар для воды!!!!");
        }
        System.out.println("Кофе готов!");
    }

    //метод подсчета, сколько чашек кофе можно приготовить
    public int calculateCups(int amountMl, int sizeCup) {
        return amountMl / sizeCup;
    }

    //метод вывод в консоль названия кофе
    public void printCoffeeName(String nameCoffee) {
        if (nameCoffee == null) {
            throw new NullPointerException("Ошибка: название кофе отсутствует");
        }
        System.out.println(nameCoffee.toUpperCase());
    }
}
