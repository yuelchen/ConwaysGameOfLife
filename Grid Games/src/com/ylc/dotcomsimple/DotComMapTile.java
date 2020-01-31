package com.ylc.dotcomsimple;

public class DotComMapTile {
    private final char HIT_VALUE = '*';
    private final char MISS_VALUE = '?';
    private final char EMPTY_VALUE = ' ';

    private char actualValue;
    private char displayValue;
    private boolean empty;
    private boolean hit;

    public DotComMapTile() {
        this.empty = true;
        this.actualValue = this.EMPTY_VALUE;
        this.displayValue = this.EMPTY_VALUE;
    }

    public DotComMapTile(char character) {
        this.empty = false;
        this.actualValue = character;
        this.displayValue = this.EMPTY_VALUE;
    }

    public char getActualValue() {
        return this.actualValue;
    }

    public char getDisplayValue() {
        return this.displayValue;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isHit() {
        return this.hit;
    }

    public String hitTile() {
        this.hit = true;
        if(Character.compare(this.displayValue, this.MISS_VALUE) == 0) {
            return "this tile was already hit, with a miss...";
        } else if(Character.compare(this.displayValue, this.HIT_VALUE) == 0) {
            return "this tile was already hit, containing the letter '" + this.actualValue + "'...";
        } else if(Character.compare(this.actualValue, this.EMPTY_VALUE) == 0) {
            this.displayValue = this.MISS_VALUE;
            return "MISS, this tile is empty!";
        } else {
            this.displayValue = this.HIT_VALUE;
            return "HIT! You got the letter '" + this.actualValue + "'!";
        }
    }
}