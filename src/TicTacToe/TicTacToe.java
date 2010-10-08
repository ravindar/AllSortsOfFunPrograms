package TicTacToe;

import java.io.*;

public class TicTacToe {

    public int gamesize = 0;            // size of gamefield
    public static int player = 0;       // who's turn is it?
    public int ki = 0;                  // depth of search
    public static int[][] gamefield;    // gamefield

    public TicTacToe(int gamesize, int beginner, int ki) {
        this.gamesize = gamesize;
        this.player = beginner;
        this.ki = ki;
        this.gamefield = new int[this.gamesize][this.gamesize];

        /** gamefield is initialised with 0's */
        // TODO: automatically initialised with o's?
        for (int i = 0; i < gamesize; i++) {
            for (int j = 0; j < gamesize; j++) {
                gamefield[i][j] = 0;
            }
        }

    }

    public static int getPlayer() {
        return player;
    }

    public void run() throws IOException {
        while (0 == Utils.won(gamefield, gamesize)) {
            turn(player);
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }

        if (Utils.won(gamefield, gamesize) == 1) {
            print(gamefield);
            System.out.println("You won!");
        } else if (Utils.won(gamefield, gamesize) == 2) {
            print(gamefield);
            System.out.println("You lost!");
        } else {
            print(gamefield);
            System.out.println("It's a Draw!");
        }
    }

    private void turn(int player) throws IOException {
        if (player == 1) {      
            print(gamefield);
            int input;
            input = Utils.readInInt("Make your turn (" + 1 + " to " + (gamesize * gamesize) + "): ");

            while (input > gamesize * gamesize || input < 1) {
                System.out.println("Wrong Input!");
                input = Utils.readInInt("Make your turn (" + 1 + " to " + (gamesize * gamesize) + "): ");
            }

            boolean check2 = true;
            for (int i = 0; i < gamesize; i++) {
                if (check2)
                    for (int j = 0; j < gamesize; j++) {
                        if (input == 1) {
                            gamefield[i][j] = 1;
                            check2 = false;
                            break;
                        } else
                            input--;
                    }
            }
        } else {                
            KI.makeTurn(gamefield, ki, gamesize);
        }
    }

    public static boolean setTurn(int x, int y) {
        if (gamefield[y][x] == 0) {
            gamefield[y][x] = player;
            return true;
        }
        return false;
    }

    public String printFigure(int x) {
        if (x == 1)
            return "X";
        else if (x == 2)
            return "O";
        else
            return "_";
    }

    public void print(int gamefield[][]) {
        for (int i = 0; i < gamesize; i++) {
            for (int j = 0; j < gamesize; j++) {
                System.out.print(printFigure(gamefield[i][j]));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}