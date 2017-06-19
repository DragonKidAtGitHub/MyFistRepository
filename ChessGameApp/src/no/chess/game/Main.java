package no.chess.game;

import no.chess.game.ChessGame;

public class Main {
    public static void main(String args[])
    {
        ChessGame game = new ChessGame();
        game.setupGame();
        game.playGame();
    }
}
