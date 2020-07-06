package tictactoe;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char[][] gameField = createGameField();
    private static boolean isFinished = false;
    private static char winner = ' ';
    private static int moves = 1;

    public static void main(String[] args) {
        while (!isFinished && moves <= 9) {
            drawGameField();
            getCoordinates();
            checkRows();
            checkColumns();
            checkDiagonals();
        }
        drawGameField();
        System.out.println(winner != ' ' ? winner + " wins" : "Draw");
        //scanner.close();
    }

    public static char[][] createGameField() {
        char[][] gameField = new char[3][3];
        for (char[] array : gameField) {
            Arrays.fill(array, ' ');
        }
        return gameField;
    }

    public static void drawGameField() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|" + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(gameField[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void getCoordinates() {
        int row;
        int column;
        System.out.print("Enter the coordinates: ");
        try {
        column = scanner.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            getCoordinates();
            return;
        }
        if (column < 0 || column > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            getCoordinates();
            return;
        }
        try {
            row = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            getCoordinates();
            return;
        }
        switch (row) {
            case 1:
                row = 2;
                break;
            case 2:
                row = 1;
                break;
            case 3:
                row = 0;
                break;
            default:
                System.out.println("Coordinates should be from 1 to 3!");
                getCoordinates();
                return;
        }
        if (gameField[row][column] == 'X' || gameField[row][column] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            getCoordinates();
        } else {
            gameField[row][column] = (moves++ % 2 == 0) ? 'O' : 'X';
        }
    }

    public static void checkRows() {
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0] != ' ') {
                if (gameField[i][0] == gameField[i][1] && gameField[i][1] == gameField[i][2]) {
                    winner = gameField[i][0];
                    isFinished = true;
                }
            }
        }
    }

    public static void checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (gameField[0][i] != ' ') {
                if (gameField[0][i] == gameField[1][i] && gameField[1][i] == gameField[2][i]) {
                    winner = gameField[0][i];
                    isFinished = true;
                }
            }
        }
    }

    public static void checkDiagonals() {
        if (gameField[1][1] != ' ') {
            if (gameField[0][0] == gameField[1][1] && gameField[1][1] == gameField[2][2] ||
                    gameField[0][2] == gameField[1][1] && gameField[1][1] == gameField[2][0]) {
                winner = gameField[1][1];
                isFinished = true;
            }
        }
    }
}
