package com.ylc.conways;

import com.ylc.grid.CharacterGrid;

import java.util.Random;

public class GameOfLifeMap extends CharacterGrid {
    private final char ALIVE = '*';
    private final char DEAD = ' ';

    public GameOfLifeMap(int height, int width) {
        super(height, width);
    }

    public void loadMap() {
        Random random = new Random();
        for(int y=0; y<this.h; y++) {
            for(int x=0; x<this.w; x++) {
                if(random.nextInt(10) > 6) {
                    this.setCoordValue(y, x, this.ALIVE);
                } else {
                    this.setCoordValue(y, x, this.DEAD);
                }
            }
        }
    }

    public boolean isCoordAlive(int y, int x) {
        if(!this.isCoordOutOfBounds(y, x) && Character.compare(this.getCoordValue(y, x).charValue(), this.ALIVE) == 0) {
            return true;
        }
        return false;
    }

    public boolean isCoordDead(int y, int x) {
        if(!this.isCoordOutOfBounds(y, x) && Character.compare(this.getCoordValue(y, x).charValue(), this.DEAD) == 0) {
            return true;
        }
        return false;
    }

    public boolean isMapDead() {
        for(int y=0; y<this.h; y++) {
            for (int x = 0; x < this.w; x++) {
                if(this.isCoordAlive(y, x)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isCoordOutOfBounds(int y, int x) {
        if(0 <= y && y < this.h && 0 <= x && x < this.w) {
            return false;
        }
        return true;
    }

    public GameOfLifeMap getMapUpdate() {
        GameOfLifeMap temp = new GameOfLifeMap(this.h, this.w);
        for(int y=0; y<this.h; y++) {
            for (int x=0; x<this.w; x++) {
                int neighbor = this.getNeighborCount(y, x);

                if(this.isCoordAlive(y, x) && (neighbor < 2 || neighbor > 3)) {
                    //if current cell is alive and has less than 2 or greater than 3 neighbors, then cell dies
                    temp.setCoordValue(y, x, this.DEAD);
                } else if(this.isCoordDead(y, x) && neighbor == 3) {
                    //if current cell is dead and has exactly 3 neighbors, then cells becomes alive
                    temp.setCoordValue(y, x, this.ALIVE);
                } else {
                    //else, there is no change to cell
                    temp.setCoordValue(y, x, this.getCoordValue(y, x));
                }
            }
        }

        return temp;
    }

    private int getNeighborCount(int y, int x) {
        int sum = 0;
        sum += this.isCoordAlive(y - 1, x - 1) ?  1 :  0; //northwest
        sum += this.isCoordAlive(y - 1, x) ?  1 :  0;        //north
        sum += this.isCoordAlive(y - 1, x + 1) ?  1 :  0; //northeast

        sum += this.isCoordAlive(y, x - 1) ?  1 :  0;        //west
        sum += this.isCoordAlive(y, x + 1) ?  1 :  0;        //east

        sum += this.isCoordAlive(y + 1, x - 1) ?  1 :  0; //southwest
        sum += this.isCoordAlive(y + 1, x) ?  1 :  0;        //south
        sum += this.isCoordAlive(y + 1, x + 1) ?  1 :  0; //southeast

        return sum;
    }

    @Override
    public void displayGrid() {
        for(int y=0; y<this.h; y++) {
            for(int x=0; x<this.w; x++) {
                System.out.print(this.getCoordValue(y, x) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}