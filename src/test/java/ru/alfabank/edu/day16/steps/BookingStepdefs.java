package ru.alfabank.edu.day16.steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.PendingException;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class BookingStepdefs {

    @Дано("в ресторане есть столики")
    public void tablesInTheRestaurantDT (DataTable dataTable) {
        // Преобразуем таблицу в список карт (ключ -> значение)
        List<Map<String, String>> input = dataTable.asMaps (String.class, String.class);

        for (Map<String, String> columns : input) {
            int number = Integer.parseInt(columns.get("номер"));
            int capacity = Integer.parseInt(columns.get("вместимость"));

            // Здесь ваша логика (например, сохранение в базу данных или список)
            System.out.printf("Cтолик №%d на %d чел%n", number, capacity);

        }
    }

    @Дано("Свободный столик на <Вместимость_столика> человек\\(|а)")
    public void tablesInTheRestaurant(int number, int capacity) {
        System.out.printf(String.format("Столик %d с вместимостью %d  чел%n", number, capacity));
    }


    @Когда("Надо забронировать столик на {int} чел")
    public void needToReserveTable(int numberOfPeople) {
        System.out.println(String.format("Клиент хочет забронироваь столик на %d чел", numberOfPeople));
    }


    @Тогда("Проверка - есть ли подходящий столик")
    public void checkForFreeTable() {
        System.out.println("Проверка - есть ли подходящий стол");
    }

    @Тогда("Осуществляется бронь столика")
    public void bookingDone() {
        System.out.println("Столик забронирован");
    }

    @Тогда("Клиент получает отказ в бронировании")
    public void bookingDecline() {
        System.out.println("Свободных столов нет");
    }

    @Когда("Клиент просит снять бронь со столика номер {int}")
    public void thenBookingCancel(int number) {
        System.out.println(String.format("Запрос об отмене брони столика с номером %d", number));
    }

    @Тогда("Проверка - можно ли снять бронь со столика номер {int}")
    public void checkBookingCancel(int number) {
        System.out.println(String.format("Проверка- можно ли отменить бронь столика с номером %d", number));
    }

    @Тогда("Бронь снята")
    public void bookingCanceled() {
        System.out.println("Бронь снята");
    }

    @Когда("Надо забронировать столик на {int} человек")
    public void needToReserveTableMore(int numberOfPeople) {

    }

    @И("Гость оставляет пожелание к брони:")
    public void wishBadGuest(DocString text) {
        String massage = text.getContent();
        System.out.println(String.format("Гость оставил пожелание к бронированию: %n%s", massage));
    }





    @Когда("Необходима бронь на <Количество_человек> человек\\(|а)")
    public void необходимаБроньНаКоличество_человекЧеловекА() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Тогда("Результат брони <бронь>")
    public void результатБрониБронь() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
