package com.ylc.dotcomsimple;

import com.ylc.grid.Grid;
import java.util.List;
import java.util.Random;

public class DotComMap implements Grid<DotComMapTile> {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private DotComMapTile[][] grid;
    private int emptyCellCount;
    private int missCellCount;
    private int size;

    public DotComMap(int size) {
        this.grid = new DotComMapTile[size][size];
        this.size = size;

        System.out.println("Creating DotComMap with a height and width of " + size);
    }

    public void loadMapWithShips(List<DotComShip> ships) {
        this.emptyCellCount = 0;
        this.missCellCount = 0;

        for(DotComShip ship : ships) {
            if(!this.setHorizontal(ship)) {
                ships.remove(ship);
                System.out.println("Could not set ship '" + ship.getName() + "' to grid so removed from list of possible ships to sink");
            }
        }

        for(int y=0; y<this.size; y++) {
            for(int x=0; x<this.size; x++) {
                if(this.grid[y][x] == null) {
                    this.grid[y][x] = new DotComMapTile();
                    this.emptyCellCount++;
                }
            }
        }
    }

    private boolean setHorizontal(DotComShip ship) {
        int retry = 0;
        do {
            int y = this.getRandom();
            int x = this.getRandomOffset(ship);
            if(isHorizontalAvailable(y, x, ship.getLength())) {
                for(int i=0; i<ship.getLength(); i++) {
                    String coord = this.alphabet.charAt(y) + "" + this.alphabet.charAt(x+i);
                    char character = ship.getName().charAt(i);

                    this.grid[y][x+i] = new DotComMapTile(character);
                    ship.setCoordinates(coord, character);
                }

                return true;
            }
        } while(retry < 3);

        return false;
    }

    private boolean isHorizontalAvailable(int y, int x, int length) {
        boolean available = true;
        if(this.size > (x+length)) {
            for (int i = 0; i < length; i++) {
                if (this.grid[y][x + i] != null) {
                    available = false;
                }
            }
        } else {
            available = false;
        }

        return available;
    }

    private int getRandom() {
        Random random = new Random();
        return random.nextInt(this.size);
    }

    private int getRandomOffset(DotComShip ship) {
        Random random = new Random();
        return random.nextInt((this.size - ship.getLength()) + 1);
    }

    public boolean updateGrid(char y, char x) {
        int yCoord = this.alphabet.indexOf(y);
        int xCoord = this.alphabet.indexOf(x);

        if(yCoord >= this.size || xCoord >= this.size) {
            System.out.println("This is not a valid answer!");
            return false;
        } else {
            String response = this.grid[yCoord][xCoord].hitTile();
            if(response.equals("MISS, this tile is empty!")) {
                this.missCellCount++;
            }
            System.out.println(response);
            return true;
        }
    }

    public boolean isGridDead() {
        for(int y=0; y<this.size; y++) {
            for(int x=0; x<this.size; x++) {
                if(!this.grid[y][x].isEmpty() && !this.grid[y][x].isHit()) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public DotComMapTile[][] getGrid() {
        return this.grid;
    }

    @Override
    public int getHeight() {
        return this.size;
    }

    @Override
    public int getWidth() {
        return this.size;
    }

    public int getEmptyCellCount() {
        return this.emptyCellCount;
    }

    public int getMissCellCount() {
        return this.missCellCount;
    }

    public void displayGrid(boolean showActualValues) {
        System.out.print("   |");
        for(int x=0; x<this.size; x++) {
            System.out.print(" " + this.alphabet.toUpperCase().charAt(x) + " |");
        }
        this.displayRowDelimiter();

        for(int y=0; y<this.size; y++) {
            for(int x=0; x<this.size; x++) {
                if(x == 0) {
                    System.out.print(" " + this.alphabet.toUpperCase().charAt(y) + " |");
                }

                if(showActualValues) {
                    System.out.print(" " + this.grid[y][x].getActualValue() + " |");
                } else {
                    System.out.print(" " + this.grid[y][x].getDisplayValue() + " |");
                }
            }
            this.displayRowDelimiter();
        }
    }

    private void displayRowDelimiter() {
        System.out.println("");
        for(int x=0; x<=this.size; x++) {
            System.out.print("---+");
        }
        System.out.println("");
    }
}