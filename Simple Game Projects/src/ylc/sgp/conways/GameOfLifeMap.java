package ylc.sgp.conways;

import ylc.sgp.grid.GridBoard;
import java.util.Random;

public class GameOfLifeMap {
    private final char LIVE_CELL = '*';
    private final char DEAD_CELL = ' ';

    public int yLength;
    public int xLength;
    private GridBoard golBoard;

    public GameOfLifeMap(int height, int width) {
        this.yLength = height;
        this.xLength = width;
        this.golBoard = new GridBoard(this.yLength, this.xLength);
    }

    public int getYLength() {
        return this.yLength;
    }

    public int getXLength() {
        return this.xLength;
    }

    public GridBoard getGOLBoard() {
        return this.golBoard;
    }

    public void generateBoard() {
        Random generator = new Random();
        for(int y = 0; y < this.yLength; y++) {
            for(int x = 0; x < this.xLength; x++) {
                int randomNumber = generator.nextInt(10);
                if(randomNumber > 6) {
                    this.golBoard.setCellValue(y, x, this.LIVE_CELL);
                }
                else {
                    this.golBoard.setCellValue(y, x, this.DEAD_CELL);
                }
            }
        }
    }

    public boolean isBoardDead() {
        for(int y = 0; y < this.yLength; y++) {
            for(int x = 0; x < this.xLength; x++) {
                if(Character.compare(this.golBoard.getCellValue(y, x), this.LIVE_CELL) == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public GridBoard playRound() {
        GridBoard temp = new GridBoard(this.yLength, this.xLength);
        for(int y = 0; y < this.yLength; y++) {
            for(int x = 0; x < this.xLength; x++) {
                temp.setCellValue(y, x, this.determineCellAlive(y, x));
            }
        }

        this.golBoard = temp;
        return this.golBoard;
    }

    private char determineCellAlive(int y, int x) {
        int count = this.getLiveNeighborsCount(y, x);

        //check if live cell can stay alive
        if(Character.compare(this.golBoard.getCellValue(y, x), this.LIVE_CELL) == 0) {
            if(count == 2 || count == 3) {
                return this.LIVE_CELL;
            }
            return this.DEAD_CELL;
        }
        //check if dead cell has enough neighbors to come back to life
        else {
            if(count == 3) {
                return this.LIVE_CELL;
            }
            return this.DEAD_CELL;
        }
    }

    private int getLiveNeighborsCount(int y, int x) {
        return this.northWestCell(y, x) + this.northCell(y, x) + this.northEastCell(y, x) + this.westCell(y, x) + this.eastCell(y, x) + this.southWestCell(y, x) + this.southCell(y, x) + this.southEastCell(y, x);
    }

    private int northWestCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y - 1, x - 1) && Character.compare(this.golBoard.getCellValue(y - 1, x - 1), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }

    private int northCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y - 1, x) && Character.compare(this.golBoard.getCellValue(y - 1, x), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }

    private int northEastCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y - 1, x + 1) && Character.compare(this.golBoard.getCellValue(y - 1, x + 1), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }

    private int westCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y, x - 1) && Character.compare(this.golBoard.getCellValue(y, x - 1), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }

    private int eastCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y, x + 1) && Character.compare(this.golBoard.getCellValue(y, x + 1), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }

    private int southWestCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y + 1, x - 1) && Character.compare(this.golBoard.getCellValue(y + 1, x - 1), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }

    private int southCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y + 1, x) && Character.compare(this.golBoard.getCellValue(y + 1, x), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }

    private int southEastCell(int y, int x) {
        if(!this.golBoard.isCellOutOfBounds(y + 1, x + 1) && Character.compare(this.golBoard.getCellValue(y + 1, x + 1), this.LIVE_CELL) == 0) {
            return 1;
        }
        return 0; //if out of bounds or has no neighbors
    }
}