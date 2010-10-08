package TicTacToe;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException {
        int ki = 0;
        int size = 0;
        int beginner;
        System.out.println("Tic Tac Toe   v1.01");
        size = Utils.readInInt("Enter size of gamefield: ");

        ki = Utils.readInInt("KI difficulty level (1 to " + size * size + "): ");
        while (ki > size * size || ki < 1) {
            System.out.println("Wrong Input!");
            ki = Utils.readInInt("KI (1 to " + size * size + "): ");
        }

        beginner = Utils.readInInt("Who starts (Press 1 for Player, 2 for Computer)? ");
        while (beginner > 2 || beginner < 1) {
            System.out.println("Wrong Input!");
            beginner = Utils.readInInt("Who starts (Press 1 for Player, 2 for Computer? ");
        }

        TicTacToe Spiel = new TicTacToe(size, beginner, ki);
        Spiel.run();

    }

}
