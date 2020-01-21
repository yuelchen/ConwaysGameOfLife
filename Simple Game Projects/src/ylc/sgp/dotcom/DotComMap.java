package ylc.sgp.dotcom;

import ylc.sgp.grid.GridBoard;

public class DotComMap {
    private final char EMPTY_CELL = '_';
    private int yLength;
    private int xLength;

    private GridBoard board;
    private DotComShip[] ships;

    public DotComMap(int height, int width) {
        this.yLength = height;
        this.xLength = width;
    }

    public int getYLength() {
        return this.yLength;
    }

    public int getxLength() {
        return this.xLength;
    }

    public GridBoard getBoard() {
        return this.board;
    }

    private void createBoard() {
        this.board = new GridBoard(this.yLength, this.xLength);
        for(int y=0; y<this.yLength; y++) {
            for(int x=0; x<this.xLength; x++) {
                this.board.setCellValue(y, x, this.EMPTY_CELL);
            }
        }
    }

    private void loadShips(String[] urls) {
        for(String name : urls) {
            int length = name.length();
            
        }
    }
}