package ru.alfabank.edu.day13;

import java.util.LinkedList;

public class AssaultQueue {

    //Объявляем поле
    private LinkedList<String> queue;

    //конструктор, инициализирующий список
    public AssaultQueue() {
        this.queue = new LinkedList<>();
    }

    //метод добавления штурмовика в конец очереди
    public void addRecruit(String name) {
        if (queue != null) {
            queue.addLast(name);
        }
    }

    //метод удаления и возврата штурмовика из начала очереди
    public String retreatRecruit() {
        return queue.pollFirst();
    }

    //вывод текущего состояния очереди
    public void ptintQueue() {
        System.out.println(queue.toString());
    }

    //Переопределяем toString() для вывода информации об очереди
    @Override
    public String toString() {
        return "Очередь на вход: " + queue;
    }
}
