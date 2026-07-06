package ru.alfabank.edu.day12;

public class BaggageDropDesk {
    private String[] availableFlights;

    public BaggageDropDesk(String[] availableFlights) {
        this.availableFlights = availableFlights;
    }

    public BaggageTicket dropBaggage (String name, String flightNumber, int weightBaggege)
        throws FlightNotFoundException, OverweightBaggageException, BaggageTagPrintException {

        if (name == null || name.isEmpty()) {
            throw new InvalidPassengerNameException("Имя не может быть null или пустым");
        }

        if (weightBaggege < 0) {
            throw new InvalidBaggageWeightException("Вес багажа не может быть отрицательным");
        }

        if (weightBaggege > 23 ) {
            throw new OverweightBaggageException("Багаж весит больше 23 кг");
        }

        if (flightNumber == "AE-404") {
            throw new BaggageTagPrintException("Сбой принтера");
        }

        boolean flightExists = false;
        if (availableFlights != null) {
            for (String flight : availableFlights) {
                if (flight != null && flight.equalsIgnoreCase(flightNumber)) {
                    flightExists = true;
                    break;
                }
            }
        }
        if (!flightExists) {
            throw new FlightNotFoundException("Рейс " + flightNumber + " не найден");
        }

        System.out.println("Введенные вами данные идеальны! Уплатите налог:-)");

        return new BaggageTicket(name, flightNumber, weightBaggege);
    }
}
