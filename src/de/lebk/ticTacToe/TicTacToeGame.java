package de.lebk.ticTacToe;

import java.util.Scanner;

public class TicTacToeGame {
    private TicTacToePlayingField field;
    private int moveCounter = 0;
    private boolean gameOn = true;
    private Scanner sc = new Scanner(System.in);

    public TicTacToeGame(TicTacToePlayingField field) {
        this.field = field;
    }


    public void play() {
        System.out.println("Spiel gestartet:");
        field.cleanPlayingField();
        field.printPlayingField();


        while (gameOn) {
            char playerWhoMoves = moveCounter % 2 == 0 ? 'x' : 'o';
            int[] move = promptPlayerInput(playerWhoMoves);

            field.enterMove(move, playerWhoMoves);
            field.printPlayingField();

            if (moveCounter >= 4 && field.isThereWinner(playerWhoMoves)) {
                System.out.println("======================");
                System.out.println("Player '" + playerWhoMoves + "' hat gewonnen");
                System.out.println("======================");
                gameOn = false;
            }

            moveCounter++;

        }

    }

    private int[] promptPlayerInput(char xo) {
        System.out.print("\nPlayer '" + xo + "' ist dran: Bitte Spielzug eingeben (z.B. a2)\n -> ");
        String inputStringMove = sc.nextLine();

        int[] moveCoordinates;
        try {
            moveCoordinates = evaluateInput(inputStringMove);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return promptPlayerInput(xo);
        }

        if (!field.isSquareEmpty(moveCoordinates)) {
            System.err.println("Das Feld ist bereits besetzt, bitte erneut probieren.");
            return promptPlayerInput(xo);
        }

        return moveCoordinates;
    }


    private int[] evaluateInput(String userInput) {
        if (userInput.length() != 2) {
            throw new IllegalArgumentException("Eingabe ungültig, (input falsche länge) bitte erneut probieren");
        }
        int i = userInput.charAt(0) - 'a';    // vorher so: - 97;
        int j = userInput.charAt(1) - '1';      // vorher so- 49;
//        System.out.println("i:" + i + " j: " + j);

        if (i < 0 || i > 2 || j < 0 || j > 2) {
            throw new IllegalArgumentException("Eingabe ungültig, (i oder j zu groß/klein) bitte erneut probieren");
        }

        return new int[]{i, j};
    }


}
