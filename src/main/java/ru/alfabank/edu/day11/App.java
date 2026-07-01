package ru.alfabank.edu.day11;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public void main() {

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Сколько воды осталось в резервуаре, мл?");

        int a = 0;
        try {
            a = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: нужно было ввести число");
            System.out.println("Введи число");
            scanner.next();
            a = scanner.nextInt();
        }
        System.out.println("------------------------");

        try {
            coffeeMachine.makeCoffee(100);
        } catch (NotEnoughWaterException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Проверка кофемашины завершена");
        }

        System.out.println("------------------------");
        try {
            coffeeMachine.calculateCups(1000,0);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: размер чашки не может быть 0");
        }

        System.out.println("------------------------");
        try {
            coffeeMachine.printCoffeeName(null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


}
