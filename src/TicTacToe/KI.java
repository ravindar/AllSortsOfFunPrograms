package TicTacToe;

public class KI {

    public static void makeTurn(int[][] gamefield, int ki, int gamesize) {
        int freeFields = 0;

        for (int i = 0; i < gamesize; i++) {
            for (int j = 0; j < gamesize; j++) {
                if (gamefield[i][j] == 0) {
                    freeFields++;
                }
            }
        }

        int turn1[] = miniMax(gamefield, ki, TicTacToe.getPlayer(), freeFields, gamesize);
        TicTacToe.setTurn(turn1[1], turn1[2]);
    }


    public static int[] miniMax(int[][] gamefield, int ki, int player, int freeFields, int gamesize) {

        int[][] tempWorth = new int[freeFields][3];
        int[] minMaxWorth = new int[3];
        int[] tempMinMaxWorth = new int[2];

        if (ki-- == 0 || freeFields == 0) {
            tempMinMaxWorth[0] = evaluateMove(gamefield, gamesize);
            minMaxWorth[0] = tempMinMaxWorth[0];
            return minMaxWorth;
        } else if (2 == Utils.won(gamefield, gamesize) || 1 == Utils.won(gamefield, gamesize) || 3 == Utils.won(gamefield, gamesize)) {
            tempMinMaxWorth[0] = evaluateMove(gamefield, gamesize);
            minMaxWorth[0] = tempMinMaxWorth[0];
            return minMaxWorth;
        } else {
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }

            int index = 0;
            int[][][] next = new int[freeFields][gamesize][gamesize];

            for (int i = 0; i < freeFields; i++) {
                for (int x = 0; x < gamesize; x++) {
                    for (int y = 0; y < gamesize; y++) {
                        next[i][x][y] = gamefield[x][y];
                    }
                }
            }

            while (index < freeFields) {
                for (int x = 0; x < gamesize; x++) {
                    for (int y = 0; y < gamesize; y++) {
                        if (index < freeFields && next[index][x][y] == 0) {
                            if (player == 1) {
                                next[index][x][y] = 2;
                            } else {
                                next[index][x][y] = 1;
                            }
                            tempWorth[index][2] = x;
                            tempWorth[index][1] = y;
                            tempMinMaxWorth = miniMax(next[index], ki, player, (freeFields - 1), gamesize);
                            tempWorth[index][0] = tempMinMaxWorth[0];
                            index++;
                        }
                    }
                }
            }
            tempMinMaxWorth[0] = 0;

            if (player == 1) {
                tempMinMaxWorth[1] = tempWorth[0][0];
                for (int i = tempMinMaxWorth[0]; i < freeFields; i++) {
                    if (tempMinMaxWorth[1] < tempWorth[i][0]) {
                        tempMinMaxWorth[0] = i;
                        tempMinMaxWorth[1] = tempWorth[i][0];
                    }
                }
            } else {
                tempMinMaxWorth[1] = tempWorth[0][0];
                for (int i = tempMinMaxWorth[0]; i < freeFields; i++) {
                    if (tempMinMaxWorth[1] > tempWorth[i][0]) {
                        tempMinMaxWorth[0] = i;
                        tempMinMaxWorth[1] = tempWorth[i][0];
                    }
                }
            }
            return tempWorth[tempMinMaxWorth[0]];
        }
    }

    public static void print(int[][] gamefield, int gamesize) {
        String buffer = "";

        for (int i = 0; i < gamesize; i++) {
            for (int j = 0; j < gamesize; j++) {
                if (gamefield[i][j] == 0) {
                    buffer += "_ ";
                } else if (gamefield[i][j] == 1) {
                    buffer += "X ";
                } else if (gamefield[i][j] == 2) {
                    buffer += "O ";
                }
            }
            System.out.println(buffer);
            buffer = "";
        }
    }

    public static int evaluateMove(int[][] next, int gamesize) {
        int min = 0;
        int max = 0;

        int[] check = new int[(2 * gamesize) + 2];

        int horizontal;
        int vertical;
        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int i = 0; i < gamesize; i++) {

            horizontal = 0;
            vertical = 0;

            for (int j = 0; j < gamesize; j++) {
                if (next[i][j] == 1) {
                    horizontal--;
                } else if (next[i][j] == 2) {
                    horizontal++;
                }
                if (next[j][i] == 1) {
                    vertical--;
                } else if (next[j][i] == 2) {
                    vertical++;
                }
            }

            if (next[i][i] == 1) {
                diagonal1--;
            } else if (next[i][i] == 2) {
                diagonal1++;
            }

            if (next[(gamesize - 1) - i][i] == 1) {
                diagonal2--;
            } else if (next[(gamesize - 1) - i][i] == 2) {
                diagonal2++;
            }

            check[i] = horizontal;
            check[gamesize + i] = vertical;
        }

        check[(2 * gamesize)] = diagonal1;
        check[(2 * gamesize) + 1] = diagonal2;

        for (int i = 0; i < check.length; i++) {
            if (max < check[i])
                max = check[i];
            else if (min > check[i])
                min = check[i];
        }
        return min + max;
    }
}
