package com.ylc.console;

import com.ylc.conways.GameOfLife;
import com.ylc.dotcomsimple.SinkADotCom;

public class Console {
    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife(5);
        gol.execute(5, 5);

        SinkADotCom sdc = new SinkADotCom();
        sdc.addShip("dot.com");
        sdc.addShip("github.com");
        sdc.addShip("hello.world");
        sdc.execute();
    }
}