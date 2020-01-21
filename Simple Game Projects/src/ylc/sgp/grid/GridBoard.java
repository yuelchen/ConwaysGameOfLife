package ylc.sgp.grid;

public class GridBoard {
    private int initial_height;
    private int initial_width;
    private char[][] board;

    public GridBoard(int height, int width) {
        this.initial_height = height;
        this.initial_width = width;
        this.board = new char[this.initial_height][this.initial_width];
    }

    public int getHeight() {
        return this.initial_height;
    }

    public int getWidth() {
        return this.initial_width;
    }

    public char[][] getBoard() {
        return this.board;
    }

    public void printBoard() {
        for(int y=0; y<this.initial_height; y++) {
            for(int x=0; x<this.initial_width; x++) {
                System.out.print(this.board[y][x] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public char getCellValue(int yCell, int xCell) {
        if(!this.isCellOutOfBounds(yCell, xCell)) {
            return this.board[yCell][xCell];
        }

        return ' ';
    }

    public boolean setCellValue(int yCell, int xCell, char value) {
        if(!this.isCellOutOfBounds(yCell, xCell)) {
            this.board[yCell][xCell] = value;
            return true;
        }

        return false;
    }

    public boolean isCellOutOfBounds(int yCell, int xCell) {
        if(0 <= yCell && 0 <= xCell && this.initial_height > yCell && this.initial_width > xCell) {
            return false;
        }

        return true;
    }
}