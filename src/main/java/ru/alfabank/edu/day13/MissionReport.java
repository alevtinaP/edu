package ru.alfabank.edu.day13;


import java.util.List;;

public class MissionReport {

    //Поля класса
    private String missionName;
    private List<Alien> capturedAliens;
    private int squadSize;

    //Создаем конструктор
    public MissionReport(String missionName, List<Alien> capturedAliens, int squadSize) {
        this.missionName = missionName;
        this.capturedAliens = capturedAliens;
        this.squadSize = squadSize;
    }


    //Задаём сеттеры
    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public void setCapturedAliens(List<Alien> capturedAliens) {
        this.capturedAliens = capturedAliens;
    }

    public void setSquadSize(int squadSize) {
        this.squadSize = squadSize;
    }

    //Переопределяем toString()
    @Override
    public String toString() {
        return  "Миссия: " + missionName + '\n' +
                "Поймано пришельцев: " + capturedAliens.size() + '\n' +
                "Размер отряда: " + squadSize;
    }
}
