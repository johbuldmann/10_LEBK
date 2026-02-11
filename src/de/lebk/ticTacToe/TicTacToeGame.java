package de.lebk.ticTacToe;

import java.util.Scanner;

public class TicTacToeGame {
    private TicTacToePlayingField field;
    private int moveCounter = 0;
    private boolean gameOn = true;
    private Scanner sc = new Scanner(System.in);

    public TicTacToeGame() {
        this.field = new TicTacToePlayingField();
    }


    // todo: überladene play methode für random start? x = x startet, o = o startet, r = random start
    //  Problem: Anzahl der Spielzüge dann unklar bzw off by one wenn ich zufällig bei 1 oder 0 starte..
    //  -> Startvalue separat speichern und dann relativ dazu die Spielzüge zähle n?


    public void play() {
        System.out.println("Spiel gestartet:");
        field.cleanPlayingField();
        field.printPlayingField();


        while (gameOn) {
            char playerWhoMoves = moveCounter % 2 == 0 ? 'X' : 'O';
            int[] move = promptPlayerInput(playerWhoMoves);

            field.enterMove(move, playerWhoMoves);
            field.printPlayingField();

            if (moveCounter >= 4 && field.isThereWinner(playerWhoMoves, move)) {
                System.out.println("======================");
                System.out.println("Player '" + playerWhoMoves + "' hat gewonnen");
                System.out.println("======================");
                gameOn = false;
            }

            if (moveCounter >= 8) {
                System.out.println("======================");
                System.out.println("Das Spiel ist unentschieden ausgegangen.");
                System.out.println("======================");
                gameOn = false;
            }

            moveCounter++;

        }

    }

    private int[] promptPlayerInput(char xo) {
        System.out.print("\nPlayer '" + xo + "' ist dran: Bitte Spielzug eingeben (z.B. a2 oder mit Numpad 1-9)\n -> ");
        String inputStringMove = sc.nextLine();

        int[] moveCoordinates;
        try {
            moveCoordinates = evaluateInput(inputStringMove);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return promptPlayerInput(xo);
        }

        if (!field.isSquareEmpty(moveCoordinates)) {
            System.err.println("Das Feld ist bereits besetzt, bitte erneut probieren.");
            return promptPlayerInput(xo);
        }

        return moveCoordinates;
    }


    // todo: alternative Eingabemethode überlegen, statt Koordinaten könnte auch das Numpad genutzt werden, also Eingabe
    //  1 bis 9 für die Felder. Am elegantesten wäre es natürlich dies beim Start des Spiels auswählen zu können.
    //  vielleicht bekommt die play() Methode eine Evaluate-Objekt übergeben, je nachdem welche Variante aufgerufen wird.
    //  oder ich mache es einfach automatisch je nach Eingabe. Wenn Länge 2 dann a2 wenn Länge 1 dann 1-9

    // todo: auch noch am anfang ein .toLowerCase und ein .trim einbauen als Normalisierung.
    private int[] evaluateInput(String userInput) {
        userInput = userInput.trim();

        int i;
        int j;

        if (userInput.length() == 1) {
            int numpadInput;
            try {
                numpadInput = Integer.parseInt(userInput);
            } catch (Exception e) {
                throw new IllegalArgumentException("Wenn Numpad-Eingabe nur eine Zahl 1-9 eingeben");
            }
            i = (numpadInput - 1) % 3;
            j = (numpadInput -1) / 3;
        } else if (userInput.length() == 2) {
            i = userInput.charAt(0) - 'a';    // vorher so: - 97;
            j = userInput.charAt(1) - '1';      // vorher so- 49;
//        System.out.println("i:" + i + " j: " + j);

            if (i < 0 || i > 2 || j < 0 || j > 2) {
                throw new IllegalArgumentException("Eingabe ungültig, (i oder j zu groß/klein) bitte erneut probieren");
            }
        } else {
            throw new IllegalArgumentException("Eingabe ungültig, (Input falsche Länge) bitte erneut probieren");
        }

        return new int[]{i, j};
    }


}

