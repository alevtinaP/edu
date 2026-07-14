package ru.alfabank.edu.day13;

import java.util.Objects;

public class Alien {
    String name;
    String planet;
    int dangerLevel;

    //задаем конструктор
    public Alien(String name, String planet, int dangerLevel) {
        this.name = name;
        this.planet = planet;
        this.dangerLevel = dangerLevel;
    }

    //Переопределяем equals() и hashCode(), чтобы пришельцы считались одинаковыми при совпадении имени и планеты
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alien alien = (Alien) o;
        return Objects.equals(name, alien.name) && Objects.equals(planet, alien.planet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, planet);
    }


    //Переопределяем toString()
    @Override
    public String toString() {
        return "Alien{" +
                "name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", dangerLevel=" + dangerLevel +
                '}';
    }
//Задаем Геттеры
    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }
}
