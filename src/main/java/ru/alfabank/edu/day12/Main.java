package ru.alfabank.edu.day12;

public class Main {
    public static void main(String[] args) {
        String[] flights = {"AE-404", "AE-500", "AE-200", "AE-401"};
        BaggageDropDesk terminal = new BaggageDropDesk(flights);

        System.out.println("Сценарии");

        // 1. успешная сдача багажа
        scenario(terminal, "Петр Петров", "AE-500", 15);

        // 2. указанного пассажиром рейса не существует в базе
        scenario(terminal, "Иван Иванов", "AE-200", 10);
        // 3. багаж слишком тяжелый
        scenario(terminal, "Катя Иванова", "AE-401", 29);

        // 4. проблема с печатью бирки
        scenario(terminal, "Коля Сидоров", "AE-404", 8);

        // 5. некорректное имя пассажира (null)
        scenario(terminal, null, "AE-200", 10);

        // 6. некорректный вес багажа (отрицательный)
        scenario(terminal, "Иван Иванов", "AE-200", -10);
    }



    private static void scenario(BaggageDropDesk terminal, String name, String flight, int weight) {
            System.out.println("Попытка сдачи: Пассажир=" + name + ", Рейс=" + flight + ", Вес=" + weight);

            try {
                BaggageTicket ticket = terminal.dropBaggage(name, flight, weight);
                System.out.println("Билет выдан: " + ticket);

            } catch (FlightNotFoundException e) {
                System.out.println("!!!! Ошибка !!!!!: Указанного пассажиром рейса не существует в базе! Сообщение: " + e.getMessage());

            } catch (OverweightBaggageException e) {
                System.out.println("!!!! Ошибка !!!!!: Багаж слишком тяжелый! Сообщение: " + e.getMessage());

            } catch (BaggageTagPrintException e) {
                System.out.println("!!!! Ошибка !!!!!: Проблема с печатью бирки. Сообщение: " + e.getMessage());

            } catch (InvalidPassengerNameException e) {
                System.out.println("Критическая ошибка !: Некорректное имя пассажира Сообщение: " + e.getMessage());

            } catch (InvalidBaggageWeightException e) {
                System.out.println("Критическая ошибка !: Некорректный вес багажа! Сообщение: " + e.getMessage());
            }

            System.out.println("--------------\n");
        }
    }



