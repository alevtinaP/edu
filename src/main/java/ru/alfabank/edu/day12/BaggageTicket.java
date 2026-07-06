package ru.alfabank.edu.day12;

public class BaggageTicket {
    private String name;
    private String flightNumber;
    private int weightBaggage;

    public BaggageTicket(String name, String flightNumber, int weightBaggage) {
        this.name = name;
        this.flightNumber = flightNumber;
        this.weightBaggage = weightBaggage;
    }

    public String getName() {
        return name;
    }
    public String getFlightNumber() {
        return flightNumber;
    }


    public int getWeightBaggage() {
        return weightBaggage;
    }

    @Override
    public String toString() {
        return "BaggageTicket{" +
                "name='" + name + '\'' +
                ", giteNumber='" + flightNumber + '\'' +
                ", weightBaggage=" + weightBaggage +
                '}';
    }


}
