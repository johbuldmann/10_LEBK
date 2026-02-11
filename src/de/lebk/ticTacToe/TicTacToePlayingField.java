package de.lebk.ticTacToe;

public class TicTacToePlayingField {
    private char[][] fieldState = new char[3][3];


    public void printPlayingField() {


        System.out.println();
        System.out.println("3 | " + fieldState[0][2] + " | " + fieldState[1][2] + " | " + fieldState[2][2] + " |");
        System.out.println("  + — + — + — +");
        System.out.println("2 | " + fieldState[0][1] + " | " + fieldState[1][1] + " | " + fieldState[2][1] + " |");
        System.out.println("  + — + — + — +");
        System.out.println("1 | " + fieldState[0][0] + " | " + fieldState[1][0] + " | " + fieldState[2][0] + " |");
        System.out.println("    a   b   c ");
        System.out.println();


    }

    public void cleanPlayingField() {
        fieldState = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    }

    public void enterMove(int[] coordinates, char input) {
        fieldState[coordinates[0]][coordinates[1]] = input;
    }

    public boolean isSquareEmpty(int[] coordinates) {
        return (fieldState[coordinates[0]][coordinates[1]]) == ' ';
    }

    public boolean isThereWinner(char xo, int[] coordinates) {

        // Alternative Herangehensweise: von der zuletzt eingegebenen Zelle
        // ausgehend in alle 8 Richtungen gehen und zählen:
        // alle 8 Richtungen als Vektor definiert.
        int[] left = {-1, 0};
        int[] right = {1, 0};
        int[] up = {0, 1};
        int[] down = {0, -1};
        int[] leftDown = {-1, -1};
        int[] rightUp = {1, 1};
        int[] leftUp = {-1, 1};
        int[] rightDown = {1, -1};

        // nur 2 und nicht drei, weil das Ausgangsfeld nicht mitgezählt wird.
        // left right
        if (checkDirection(xo, coordinates, left) + checkDirection(xo, coordinates, right) >= 2) {
            return true;
        }

        // up down
        if (checkDirection(xo, coordinates, up) + checkDirection(xo, coordinates, down) >= 2) {
            return true;
        }

        // leftDown rightUp
        if (checkDirection(xo, coordinates, leftDown) + checkDirection(xo, coordinates, rightUp) >= 2) {
            return true;
        }

        // leftUp rightDown
        if (checkDirection(xo, coordinates, leftUp) + checkDirection(xo, coordinates, rightDown) >= 2) {
            return true;
        }

        // nichts gefunden also kein Winner:
        return false;
    }

    private int checkDirection(char xo, int[] coordinates, int[] direction) {
        int count = 0;
        // methode hier auslagern: in eine Richtung gehen:
        // hier muss ich mit 1 und nicht mit 0 starten, sonst wird Startfeld immer mitgezählt.
        for (int i = 1; i < 3; i++) {
            int a = coordinates[0] + i * direction[0];
            int b = coordinates[1] + i * direction[1];

            if (a >= 0 && a <= 2 && b >= 0 && b <= 2) {
                if (fieldState[a][b] == xo) {
                    count++;
                } else {
                    // Feld = Gegner, oder auch wichtig: Feld = leer
                    break;
                }
            } else {
                // Spielfeldrand erreich, kann abbrechen die Richtung
                break;
            }
        }
        return count;
    }

    // veraltet, überprüfe jetzt auch in tictactoe mit der Check in alle Richtungen Variante.
    public boolean isThereWinner(char xo) {
        // Diagonalen Überprüfen noch nicht implementiert


        for (int i = 0; i < 3; i++) {
            int rowCounter = 0;
            int colCounter = 0;

            for (int j = 0; j < 3; j++) {
                if (fieldState[i][j] == xo) {
                    rowCounter++;
                }
                if (fieldState[j][i] == xo) {
                    colCounter++;
                }
            }
            if (rowCounter == 3 || colCounter == 3) {
                return true;
            }
        }

        // falls alles oben noch nicht gegriffen hat dann:
        return false;
    }
}