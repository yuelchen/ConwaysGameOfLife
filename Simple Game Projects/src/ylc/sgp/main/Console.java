package ylc.sgp.main;

import ylc.sgp.conways.GameOfLife;

public class Console {
    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife();
        gol.playGame(5, 10);
    }
}