package ru.alfabank.edu.day16.steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.PendingException;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.cucumber.datatable.DataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookingStepdefs {

    private final Map<String, Object> stepContext = new HashMap<>();

    @Дано("в ресторане есть столики")
    public void tablesInTheRestaurantDT(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : table) {
            int number = Integer.parseInt(columns.get("номер"));
            int capacity = Integer.parseInt(columns.get("вместимость"));

            System.out.printf("Cтолик №%d на %d чел%n", number, capacity);
        }
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
// Структура сценария

    @Дано("Свободный столик на {string} мест")
    public void setOutlineTableCapacity(String capacity) {
        stepContext.put("Вместимость_столика", Integer.parseInt(capacity));
        System.out.printf("Есть свободный столик на: %s мест%n", capacity);
    }

    @Когда("Необходима бронь на {string} человек")
    public void setOutlineGuestsCount(String guests) {
        stepContext.put("Количество_человек", Integer.parseInt(guests));
        System.out.printf("Необходима бронь на количество человек: %s%n", guests);
    }

    @Тогда("Результат брони должен быть {string}")
    public void verifyOutlineBookingResult(String expectedResult) {
        System.out.printf("Столик может быть забронирован?: %s%n", expectedResult);
    }
}
