package ru.alfabank.edu.day15;

import java.util.ArrayList;
import java.util.List;


public class GameRental {

    private final List<BoardGame> boardGameCatalog = new ArrayList<>();

    public List<BoardGame> getBoardGameCatalog() {
        return boardGameCatalog;
    }

    public void addGame(BoardGame game) {
        if (game == null) {
            throw new IllegalArgumentException("Название игры не может быть null");
        }
        for (BoardGame boardGame : boardGameCatalog) {
            if (boardGame.getTitle().equals(game.getTitle())) {
                throw new IllegalArgumentException("Такая игра уже существует");
            }
        }
        boardGameCatalog.add(game);
    }

    public BoardGame searchGame(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Название игры не может быть null");
        }
        for (BoardGame game : boardGameCatalog) {
            if (title.equals(game.getTitle())) {
                return game;
            }
        }
        return null;
    }

    public void rentGame(String title, int customerAge) {
        BoardGame game = searchGame(title);
        if (game == null) {
            throw new IllegalArgumentException("Игра не найдена");
        }
        if (game.isRented()) {
            throw new IllegalStateException("Игра занята");
        }
        game.setRented(true);
    }

    public void returnGame(String title) {
        BoardGame game = searchGame(title);
        if (game == null) {
            throw new IllegalArgumentException("Игра не найдена");
        }
        if (!game.isRented()) {
            throw new IllegalStateException("Игра не занята");
        }

        game.setRented(false);
    }

    public int calculateCost(String title, int rentalDays) {
        BoardGame boardGame = searchGame(title);
        if (boardGame == null) {
            throw new IllegalArgumentException("Игра отсутствует в каталоге");
        }
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Количество дней аренды <=0");
        }
        return rentalDays * boardGame.getRentalCost();
    }

    public void reset() {
        for (BoardGame game : boardGameCatalog) {
            game.setRented(false);
        }
    }
}


