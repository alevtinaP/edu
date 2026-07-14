package ru.alfabank.edu.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    static void main() {

        //1.1-1.2 Создаем ArrayList<Alien> и заполняем его данными. Два из них должны иметь одинаковые имя и планету, но разный dangerLevel.
        ArrayList<Alien> aliens = new ArrayList<>();

        aliens.add(new Alien("Зорг", "Брутал", 4));
        aliens.add(new Alien("Ирид", "Аква-Велес", 10));
        aliens.add(new Alien("Леора", "Элизиум Прайм", 8));
        aliens.add(new Alien("Вормикс", "Нибирум", 1));
        aliens.add(new Alien("Зорг", "Брутал", 9));

        //1.3 Проверка на дубликаты
        boolean hasDuplicates = false;
        HashSet<Alien> uniqueAliens = new HashSet<>();

        for (Alien alien : aliens) {
            if (!uniqueAliens.add(alien)) {
                hasDuplicates = true;
                System.out.println("Обнаружен дубликат: " + alien.getName() + " с планеты " + alien.getPlanet());
                System.out.println();
                break;
            }
        }

        //1.4 Выводим исходный список
        System.out.println(aliens.toString());
        System.out.println("=============== ЗАДАНИЕ 2 ===============\n");

        //2. Создание экземпляра Формирования отрядов
        SquadManager squadManager = new SquadManager();

        //2. Вызываем метод демонстрации списков
        squadManager.demonstrateListCreations();
        System.out.println("=============== ЗАДАНИЕ 3 ===============\n");

        //3. Создаём ArrayList трусов и обычных бойцов для первого фильтра трусов
        ArrayList<String> cowards1 = new ArrayList<>(List.of("Трус Катя", "Саша", "Витя", "Витал", "Трус Лена"));

        //Передаём этот лист в фильтр трусов №1
        squadManager.filterOutCowards1(cowards1);
        System.out.println();

        //3.1 Создаём ArrayList трусов и обычных бойцов для второго фильтра трусов
        ArrayList<String> cowards2 = new ArrayList<>(List.of("Сашка", "Трус Витал", "Саша", "Миша", "Трус Вовка"));

        //Передаём этот лист в фильтр трусов №2
        squadManager.filterOutCowards2(cowards2);
        System.out.println("=============== ЗАДАНИЕ 4 ===============\n");

        //4.1 Создаём объект очереди бойцов
        AssaultQueue assaultQueue = new AssaultQueue();

        //4.2 Добавляем в очередь пять человек
        assaultQueue.addRecruit("Васька");
        assaultQueue.addRecruit("Петька");
        assaultQueue.addRecruit("Игорек");
        assaultQueue.addRecruit("Пашка");
        assaultQueue.addRecruit("Сярожка");

        //Выводим текущее состояние очереди
        assaultQueue.printQueue();
        System.out.println();

        //4.3 Имитируем уход двух человек из начала очереди с выводом их имён
        System.out.println(assaultQueue.retreatRecruit());
        System.out.println(assaultQueue.retreatRecruit());
        System.out.println();

        //Выводим текущее состояние очереди
        assaultQueue.printQueue();
        System.out.println();

        //4.4 Имитируем добавление трех новых человек в конец очереди
        assaultQueue.addRecruit("Витя1");
        assaultQueue.addRecruit("Витя2");
        assaultQueue.addRecruit("Витя3");

        //Выводим итоговое состояние очереди
        assaultQueue.printQueue();
        System.out.println("=============== ЗАДАНИЕ 5 ===============\n");

        //5.1  Создаем первый объект отчёта и выведите его на экран.
        List<Alien> capturedAliens = new ArrayList<>();
        capturedAliens.addAll(aliens);
        MissionReport missionReport1 = new MissionReport("Штурм зоны 51", capturedAliens, 50);
        System.out.println(missionReport1);
        System.out.println();

        //5.2  Создаем второй объект отчёта и выведите его на экран.
        MissionReport missionReport2 = new MissionReport("Штурм зоны 51", capturedAliens, 50);
        System.out.println(missionReport2);
        System.out.println();


        //5.3  Сравните их через == и через equals(). Выведите результаты обоих сравнений..
        System.out.println("Объекты отчётов одинаковы? " + (missionReport1 == missionReport2 ? "Да" : "Нет"));
        System.out.println("Объекты отчётов одинаковы? " + (missionReport1.equals(missionReport2) ? "Да" : "Нет"));
    }
    }
