package de.lebk.connect4.connect4;

import java.util.Scanner;

public class Connect4Game {
    private Connect4PlayingField field;
    private int moveCounter = 0;
    private boolean gameOn = true;
    private Scanner sc = new Scanner(System.in);

    public Connect4Game() {
        this.field = new Connect4PlayingField();
    }

    public void play() {
        System.out.println("Spiel gestartet:");
        field.cleanPlayingField();
        field.printPlayingField();

        while (gameOn) {
//            String playerWhoMoves = moveCounter % 2 == 0 ? " X " : " O ";
            String playerWhoMoves = moveCounter % 2 == 0 ? " 🔴" : " 🔵";
            int[] move = promptPlayerInput(playerWhoMoves);

            field.enterMove(move, playerWhoMoves);
            field.printPlayingField();

            if (moveCounter >= 4 && field.isThereWinner(playerWhoMoves, move)) {
                System.out.println("======================");
                System.out.println("Player '" + playerWhoMoves + "' hat gewonnen");
                System.out.println("======================");
                gameOn = false;
            }

            if (moveCounter >= 42) {
                System.out.println("======================");
                System.out.println("Das Spiel ist unentschieden ausgegangen.");
                System.out.println("======================");
                gameOn = false;
            }
            moveCounter++;
        }
    }

    private int[] promptPlayerInput(String xo) {
        System.out.print("\nPlayer '" + xo + "' ist dran: Bitte Spielzug eingeben (z.B. 1 bis 7)\n -> ");
        String inputStringMove = sc.nextLine();

        int collumnIndex;
        try {
            collumnIndex = evaluateInput(inputStringMove);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return promptPlayerInput(xo);
        }

        int rowIndex = field.findNextFreeRow(collumnIndex);
        if (rowIndex == -1) {
            System.err.println("Die Spalte ist bereits voll, bitte andere Spalte angeben!");
            return promptPlayerInput(xo);
        }

        return new int[]{rowIndex, collumnIndex};
    }

    private int evaluateInput(String userInput) {
        userInput = userInput.trim();
        if (userInput.length() != 1) {
            throw new IllegalArgumentException("Eingabe ungültig, (Input falsche Länge) bitte erneut probieren");
        }

        int numberInput;
        try {
            numberInput = Integer.parseInt(userInput);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bei Eingabe nur eine Zahl 1-7 eingeben");
        }

        if (numberInput < 1 || numberInput > 7) {
            throw new IllegalArgumentException("Eingabe ungültig, (i oder j zu groß/klein) bitte erneut probieren");
        }

        numberInput--;

        return numberInput;
    }
}
