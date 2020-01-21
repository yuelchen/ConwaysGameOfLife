package ylc.sgp.conways;

import ylc.sgp.grid.GridBoard;
import java.util.Arrays;

public class GameOfLife {
    private GameOfLifeMap map;
    private GridBoard previousMap;
    private GridBoard cachedMap;
    private int turns;

    public void playGame(int height, int width) {
        this.map = new GameOfLifeMap(height, width);
        this.previousMap = new GridBoard(height, width);
        this.cachedMap = new GridBoard(height, width);

        this.start(height, width);
        this.run();
        this.end();
    }

    private void start(int height, int width) {
        System.out.println("Generating board with '" + height + "' rows and '" + width + "' columns: ");
        this.map.generateBoard();
        this.map.getGOLBoard().printBoard();
    }

    private boolean isOnRepeat() {
        return (Arrays.deepEquals(this.cachedMap.getBoard(), this.previousMap.getBoard()) || Arrays.deepEquals(this.map.getGOLBoard().getBoard(), this.cachedMap.getBoard()));
    }

    private void run() {
        int turns = 0;

        do {
            this.previousMap = this.cachedMap;
            this.cachedMap = this.map.getGOLBoard();
            this.map.playRound();
            this.turns++;

            System.out.println("Round " + this.turns + ": ");
            this.map.getGOLBoard().printBoard();

            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                System.out.println("[GameOfLife] run - Unable to sleep for 500 ms");
            }
        }
        while(!this.map.isBoardDead() && !this.isOnRepeat());
    }

    private void end() {
        if(this.isOnRepeat()) {
            System.out.println("Game of life ran for " + this.turns + " turns before final sustainable ecosystem");
        }
        else {
            System.out.println("Game of life ran for " + this.turns + " turns before dead ecosystem");

        }
    }
}