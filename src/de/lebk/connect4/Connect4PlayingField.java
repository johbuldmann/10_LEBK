package de.lebk.connect4;

public class Connect4PlayingField {
    private String[][] fieldState = new String[6][7];

    public void printPlayingField() {
        System.out.println();
        System.out.println(" |"+fieldState[5][0]+"|"+fieldState[5][1]+"|"+fieldState[5][2]+"|"+fieldState[5][3]+"|"+fieldState[5][4]+"|"+fieldState[5][5]+"|"+fieldState[5][6]+"|");
        System.out.println(" |"+fieldState[4][0]+"|"+fieldState[4][1]+"|"+fieldState[4][2]+"|"+fieldState[4][3]+"|"+fieldState[4][4]+"|"+fieldState[4][5]+"|"+fieldState[4][6]+"|");
        System.out.println(" |"+fieldState[3][0]+"|"+fieldState[3][1]+"|"+fieldState[3][2]+"|"+fieldState[3][3]+"|"+fieldState[3][4]+"|"+fieldState[3][5]+"|"+fieldState[3][6]+"|");
        System.out.println(" |"+fieldState[2][0]+"|"+fieldState[2][1]+"|"+fieldState[2][2]+"|"+fieldState[2][3]+"|"+fieldState[2][4]+"|"+fieldState[2][5]+"|"+fieldState[2][6]+"|");
        System.out.println(" |"+fieldState[1][0]+"|"+fieldState[1][1]+"|"+fieldState[1][2]+"|"+fieldState[1][3]+"|"+fieldState[1][4]+"|"+fieldState[1][5]+"|"+fieldState[1][6]+"|");
        System.out.println(" |"+fieldState[0][0]+"|"+fieldState[0][1]+"|"+fieldState[0][2]+"|"+fieldState[0][3]+"|"+fieldState[0][4]+"|"+fieldState[0][5]+"|"+fieldState[0][6]+"|");
        System.out.println(" + — + — + — + — + — + — + — +");
        System.out.println("   1   2   3   4   5   6   7  ");
        System.out.println();
    }

    public void cleanPlayingField() {
        fieldState = new String[][] {
                {" . ", " . ", " . ", " . ", " . ", " . ", " . "},
                {" . ", " . ", " . ", " . ", " . ", " . ", " . "},
                {" . ", " . ", " . ", " . ", " . ", " . ", " . "},
                {" . ", " . ", " . ", " . ", " . ", " . ", " . "},
                {" . ", " . ", " . ", " . ", " . ", " . ", " . "},
                {" . ", " . ", " . ", " . ", " . ", " . ", " . "}
        };
    }


    public void enterMove(int[] coordinates, String input) {
       fieldState[coordinates[0]][coordinates[1]] = input;
    }

    public int findNextFreeRow(int collumIndex) {

        for (int i = 0; i < fieldState.length; i++) {
            if (fieldState[i][collumIndex].equals(" . ")) {
                return i;
            }
        }

        // falls kein Feld leer, dann gebe ich -1 zurück und prompte für eine neue Zeile.
        return -1;
    }


    public boolean isThereWinner(String xo, int[] coordinates) {

        int winCondition = 3;   // bei vier gewinnt ist das 4-1
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
        if (checkDirection(xo, coordinates, left) + checkDirection(xo, coordinates, right) >= winCondition) {
            return true;
        }

        // up down
        if (checkDirection(xo, coordinates, up) + checkDirection(xo, coordinates, down) >= winCondition) {
            return true;
        }

        // leftDown rightUp
        if (checkDirection(xo, coordinates, leftDown) + checkDirection(xo, coordinates, rightUp) >= winCondition) {
            return true;
        }

        // leftUp rightDown
        if (checkDirection(xo, coordinates, leftUp) + checkDirection(xo, coordinates, rightDown) >= winCondition) {
            return true;
        }

        // nichts gefunden also kein Winner:
        return false;
    }

    private int checkDirection(String xo, int[] coordinates, int[] direction) {
        int count = 0;
        // methode hier auslagern: in eine Richtung gehen:
        // hier muss ich mit 1 und nicht mit 0 starten, sonst wird Startfeld immer mitgezählt.
        for (int i = 1; i < 4; i++) {
            int a = coordinates[0] + i * direction[0];
            int b = coordinates[1] + i * direction[1];

            if (a >= 0 && a < 6 && b >= 0 && b < 7) {
                if (fieldState[a][b].equals(xo)) {
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
}