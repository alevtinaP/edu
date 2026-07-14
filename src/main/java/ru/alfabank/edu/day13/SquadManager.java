package ru.alfabank.edu.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SquadManager {

    //2. создание метода демонстрации
    public void demonstrateListCreations() {


        ArrayList<String> mainSquad = new ArrayList(List.of("Петя", "Иван", "Костя", "Миша"));
        List<String> supportSquad = Arrays.asList("Хелп1", "Хелп2", "Хелп3");
        List<String> eliteSquad = List.of("Элит", "Элит2");

        try {
            mainSquad.add("Леша");
            System.out.println("Добавлено успешно. Текущий состав: " + mainSquad);
        } catch (Exception e) {
            System.out.println("Ошибка добавления: " + e.getClass().getSimpleName());
        }

        try {
            mainSquad.remove("Костя");
            System.out.println("Удалено успешно. Текущий состав: " + mainSquad);
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getClass().getSimpleName());
        }

        try {
            supportSquad.add("Хелп4");
            System.out.println("Добавлено успешно. Текущий состав: " + supportSquad);
        } catch (Exception e) {
            System.out.println("Ошибка добавления: " + e.getClass().getSimpleName());
        }

        try {
            supportSquad.remove("Хелп1");
            System.out.println("Удалено успешно. Текущий состав: " + supportSquad);
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getClass().getSimpleName());
        }

        try {
            eliteSquad.add("Элит3");
            System.out.println("Добавлено успешно. Текущий состав: " + eliteSquad);
        } catch (Exception e) {
            System.out.println("Ошибка добавления: " + e.getClass().getSimpleName());
        }

        try {
            eliteSquad.remove("Элит2");
            System.out.println("Удалено успешно. Текущий состав: " + eliteSquad);
        } catch (Exception e) {
            System.out.println("Ошибка удаления: " + e.getClass().getSimpleName());
        }
    }

    //3. Отсеивание Трусов
    public void filterOutCowards1(List<String> squad) {
        System.out.println(squad.toString());
        Iterator<String> iterator = squad.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name != null && name.startsWith("Трус")) {
                iterator.remove();
            }
        }
        System.out.println(squad.toString());

    }

    //3.1 Отсеивание Трусов ( Бонус)

    public void filterOutCowards2(List<String> squad) {
        System.out.println(squad.toString());
        squad.removeIf(name -> name != null && name.startsWith("Трус"));
        System.out.println(squad.toString());
    }
}
