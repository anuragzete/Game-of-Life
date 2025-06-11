package com.project.game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Logic {
    private final GameOfLife.firstGen FIRSTGEN;
    private final int ROW = 10;
    private final int COL = 10;
    private int[][] grid = new int[ROW][COL];
    private int[][] currGen = new int[ROW][COL];
    private int[][] prevGen = new int[ROW][COL];
    private Terminal terminal;

    Logic(GameOfLife.firstGen firstGen) {
        this.FIRSTGEN = firstGen;
        try {
            terminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(new TerminalSize(COL, ROW))
                    .createTerminal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.currGen = getFirstGen();
        startGame();
    }

    private void startGame() {
        try {
            while (true) {
                logic();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void logic() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                prevGen[i][j] = currGen[i][j];
            }
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                int neighbour = checkNeighbourCount(i, j);
                if (prevGen[i][j] == 1) {
                    currGen[i][j] = (neighbour == 2 || neighbour == 3) ? 1 : 0;
                } else {
                    currGen[i][j] = (neighbour == 3) ? 1 : 0;
                }

            }
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                grid[i][j] = currGen[i][j];
            }
        }

        print(grid);
    }

    private void print(int[][] grid) {
        try {
            terminal.clearScreen();
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    terminal.setCursorPosition(j, i);
                    terminal.putCharacter(grid[i][j] == 1 ? '■' : '.');
                }
            }
            terminal.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int checkNeighbourCount(int row, int col) {
        int count = 0;
        int rm = row - 1;
        int rp = row + 1;
        int cm = col - 1;
        int cp = col + 1;

        if (rm >= 0 && prevGen[rm][col] == 1) { count++; }
        if (rp < ROW && prevGen[rp][col] == 1) { count++; }
        if (cm >= 0 && prevGen[row][cm] == 1) { count++; }
        if (cp < COL && prevGen[row][cp] == 1) { count++; }
        if (rm >= 0 && cp < COL && prevGen[rm][cp] == 1) { count++; }
        if (rm >= 0 && cm >= 0 && prevGen[rm][cm] == 1) { count++; }
        if (rp < ROW && cp < COL && prevGen[rp][cp] == 1) { count++; }
        if (rp < ROW && cm >= 0 && prevGen[rp][cm] == 1) { count++; }

        return count;
    }

    private int[][] getFirstGen() {
        return (switch(FIRSTGEN) {
            case BLOCK -> new int[][]
                    {
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,1,1,1,0,0,0,0},
                            {0,0,0,1,1,1,0,0,0,0},
                            {0,0,0,1,1,1,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0}
                    };
            case BLINKER -> new int[][]
                    {
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,1,1,1,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0}
                    };
            case GLIDER -> new int[][]
                    {
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,1,0,0,0,0,0,0,0},
                            {0,0,0,1,0,0,0,0,0,0},
                            {0,0,0,0,1,0,0,0,0,0},
                            {0,0,0,0,0,1,0,0,0,0},
                            {0,0,0,0,0,0,1,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0}
                    };
            case GOSPER_GLIDER_GUN -> new int[][]
                    {
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,1,1,0,0,0,0,0},
                            {0,0,1,0,0,1,0,0,0,0},
                            {0,0,1,0,0,1,0,0,0,0},
                            {0,0,0,1,1,0,0,0,0,0},
                            {0,0,0,0,0,0,0,1,1,0},
                            {0,0,0,0,0,0,1,0,0,1},
                            {0,0,0,0,0,0,0,1,1,1},
                            {0,0,0,0,0,0,0,0,0,0}
                    };
            case R_PENTOMINO -> new int[][]
                    {
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,1,1,0,0,0,0,0},
                            {0,0,1,1,0,0,0,0,0,0},
                            {0,0,0,1,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0,0}
                    };
        });
    }

}
