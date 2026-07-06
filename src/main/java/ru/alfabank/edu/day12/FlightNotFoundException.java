package ru.alfabank.edu.day12;

public class FlightNotFoundException extends AirportServiceException {
    public FlightNotFoundException(String massage) {
        super(massage);
    }
}
