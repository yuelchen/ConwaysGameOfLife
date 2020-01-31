package com.ylc.dotcomsimple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotComShip {
    private final String name;
    private Map<String, Character> coordinates;
    private List<String> damaged;

    public DotComShip(String name) {
        this.name = name;
        this.coordinates = new HashMap<String, Character>();
        this.damaged = new ArrayList<String>();
    }

    public String getName() {
        return this.name;
    }

    public int getLength() {
        return this.name.length();
    }

    public void setCoordinates(String coordinate, char character) {
        this.coordinates.put(coordinate, character);
    }

    public boolean isAtCoordinate(String coordinate) {
        return this.coordinates.containsKey(coordinate);
    }

    public char getCharacterAtCoordinate(String coordinate) {
        return this.coordinates.get(coordinate);
    }

    //ended up not using - might be more useful in other approach
    public boolean containsCoordinate(String coordinate) {
        if(this.coordinates.containsKey(coordinate)) {
            if(!this.damaged.contains(coordinate)) {
                this.damaged.add(coordinate);
            }
            return true;
        }

        return false;
    }

    //ended up not using - might be more useful in other approach
    public boolean isSunk() {
        return this.coordinates.size() == this.damaged.size();
    }

    public void printCoordinates() {
        for(Map.Entry<String, Character> coordinate : this.coordinates.entrySet()) {
            System.out.print("[" + coordinate.getKey() + "]: " + coordinate.getValue() + ", ");
        }
        System.out.println("");
    }
}