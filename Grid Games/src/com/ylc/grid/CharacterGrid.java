package com.ylc.grid;

public abstract class CharacterGrid implements Grid<Character> {
    private Character[][] grid;
    protected int h;
    protected int w;

    public CharacterGrid(int height, int width) {
        this.grid = new Character[height][width];
        this.h = height;
        this.w = width;
    }

    public Character[][] getGrid() {
        return this.grid;
    }

    public void setGrid(Character[][] newGrid) {
        this.grid = newGrid;
    }

    public int getHeight() {
        return this.h;
    }

    public int getWidth() {
        return this.w;
    }

    protected Character getCoordValue(int y, int x) {
        return this.grid[y][x];
    }

    protected void setCoordValue(int y, int x, Character value) {
        this.grid[y][x] = value;
    }

    public abstract void displayGrid();
}