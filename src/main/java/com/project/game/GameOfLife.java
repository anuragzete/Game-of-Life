package com.project.game;

import com.github.lalyos.jfiglet.FigletFont;

public class GameOfLife {
    enum firstGen{
        BLOCK, BLINKER, GLIDER, GOSPER_GLIDER_GUN, R_PENTOMINO
    }

    public static void main(String[] args) {
        try {
            String figFont = FigletFont.convertOneLine("Game of Life");
            System.out.println(figFont);

            new Logic(firstGen.GOSPER_GLIDER_GUN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


