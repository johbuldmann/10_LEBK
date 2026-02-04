package de.lebk.ticTacToe;

public class TicTacToeMain {
    public static void main(String[] args) {

        TicTacToePlayingField field = new TicTacToePlayingField();
        TicTacToeGame game = new TicTacToeGame(field);
        game.play();

    }

}
