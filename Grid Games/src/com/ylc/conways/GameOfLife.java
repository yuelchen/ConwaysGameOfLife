package com.ylc.conways;

import java.util.Arrays;

public class GameOfLife {
    private GameOfLifeMap map;
    private GameOfLifeMap[] cachedMaps;
    private int cacheSize;
    private int cacheIndex;
    private int cycles;

    public GameOfLife(int maxCacheSize) {
        this.cacheSize = maxCacheSize;
    }

    public void execute(int height, int width) {
        this.map = new GameOfLifeMap(height, width);
        this.cachedMaps = new GameOfLifeMap[this.cacheSize];
        this.cacheIndex = 0;
        this.cycles = 0;

        this.map.loadMap();
        System.out.println("Game of Life, Initial Grid: ");
        this.map.displayGrid();

        this.runCycles(height, width);
        this.end();
    }

    private void runCycles(int height, int width) {
        boolean endOfCycle = false;

        do {
            this.addCachedMap();
            GameOfLifeMap temp = this.map.getMapUpdate();
            endOfCycle = temp.isMapDead();

            if(this.isMapCached(temp)) {
                endOfCycle = true;
            } else {
                this.map.setGrid(temp.getGrid());
                this.cycles++;

                System.out.println("Cycle " + this.cycles + ": ");
                this.map.displayGrid();
                this.sleep();
            }
        } while(!endOfCycle);
    }

    private void addCachedMap() {
        this.cachedMaps[this.cacheIndex] = this.map;
        if(this.cacheIndex < this.cacheSize - 1) {
            this.cacheIndex++;
            //System.out.println("Map has been cached at index " + (this.cacheIndex - 1) + " and cached index incremented to index " + this.cacheIndex);
        } else {
            this.cacheIndex = 0;
            //System.out.println("Map has been cached at index " + (this.cacheSize - 1) + " and cached index has returned to index " + this.cacheIndex);
        }
    }

    private boolean isMapCached(GameOfLifeMap temp) {
        for(GameOfLifeMap cached : this.cachedMaps) {
            if (cached != null && Arrays.deepEquals(temp.getGrid(), cached.getGrid())) {
                return true;
            }
        }
        return false;
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep call ran into InterruptedException and failed to sleep for 500 ms");
        }
    }

    private void end() {
        if(this.map.isMapDead()) {
            System.out.println("Game of Life starting map has perish after " + this.cycles + " cycles");
        } else {
            System.out.println("Game of Life starting map has become sustainable after " + this.cycles + " cycles");
        }
    }
}