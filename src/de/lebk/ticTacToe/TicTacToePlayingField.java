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


    public boolean isThereWinner(char xo) {

        // todo: Diagonalen Überprüfen noch implementieren

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

    public boolean isThereWinner(char xo, int[] coordinates) {

        // Alternative Herangehensweise: von der zuletzt eingegebenen Zelle
        // ausgehend in alle 8 Richtungen gehen und zählen:
        int x = coordinates[1];
        int y = coordinates[0];

        for (int i = 0; i < 3; i++) {
            int rowCounter = 1;
            int colCounter = 1;

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

        return false;
    }
}
