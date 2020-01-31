package com.ylc.dotcomsimple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SinkADotCom {
    private List<DotComShip> ships;
    private DotComMap map;
    private int cycles;

    public SinkADotCom() {
        this.ships = new ArrayList<DotComShip>();
    }

    public void addShip(String dotComName) {
        DotComShip ship = new DotComShip(dotComName);
        this.ships.add(ship);
        System.out.println("Added " + dotComName + " to list of ships");
    }

    public void execute() {
        this.start();
        this.cycles = 0;

        this.runCycles();
        this.end();
    }

    private void start() {
        this.map = new DotComMap(this.getMinMapSize() + 2);
        this.map.loadMapWithShips(this.ships);
        this.map.displayGrid(false);

        for(DotComShip ship : this.ships) {
            ship.printCoordinates();
        }
    }

    private void runCycles() {
        this.cycles++;

        do {
            String choice = this.getInput().toLowerCase();
            if(this.map.updateGrid(choice.charAt(0), choice.charAt(1))) {
                this.checkShips(choice);

                System.out.println("");
                this.map.displayGrid(false);
            }

        } while(!this.map.isGridDead());
    }

    private String getInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print("[" + this.cycles + "] Enter your choice (i.e. ab - a is y-axis and b is x-axis): ");

        String input = scan.next();
        if(input.length() != 2) {
            System.out.println(input + " is not a valid answer, should only be two characters... try again");
            return this.getInput();
        } else {
            return input;
        }
    }

    private void checkShips(String input) {
        Iterator<DotComShip> iterator = this.ships.iterator();
        while(iterator.hasNext()) {
            DotComShip ship = iterator.next();
            ship.containsCoordinate(input);
            if(ship.isSunk()) {
                System.out.println("DotComShip with name " + ship.getName() + " has been sunk!");
                iterator.remove();
            }
        }
    }

    private void end() {
        double emptyTotal = (double) this.map.getEmptyCellCount();
        double emptyRemaining = (double) (this.map.getEmptyCellCount() - this.map.getMissCellCount());

        double score = emptyRemaining / emptyTotal * 100;
        System.out.println("Number of turns: " + this.cycles);
        System.out.println("Score " + emptyRemaining + " out of " + emptyTotal + ": " + score + "%");
    }

    private int getMinMapSize() {
        int min = 1;
        for(DotComShip ship : this.ships) {
            min = min < ship.getLength() ? ship.getLength() : min;
        }

        return min;
    }
}