package ru.alfabank.edu.day15;

public class BoardGame {
    private String title;
    private int minAge;
    private int rentalCost;
    private boolean rented;

    public BoardGame(String title, int minAge, int rentalCost) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Название игры не может быть пустым (null) или незаполненным");
        }
        if (minAge < 0) {
            throw new IllegalArgumentException("Минимальный возраст не может быть отрицательным");
        }
        if (rentalCost <= 0) {
            throw new IllegalArgumentException("Стоимость аренды должна быть больше 0");
        }

        this.title = title;
        this.minAge = minAge;
        this.rentalCost = rentalCost;
    }

    public String getTitle() {
        return title;
    }

    public int getMinAge() {
        return minAge;
    }

       public int getRentalCost() {
        return rentalCost;
    }

    public boolean isRented() {
        return rented;
    }

    public boolean canBeRentedBy(int age) {
        return age >= minAge;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }




}
