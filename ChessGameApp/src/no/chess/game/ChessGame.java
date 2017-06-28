package no.chess.game;

import no.chess.game.board.*;
import no.chess.game.piece.*;
import no.chess.game.player.*;

import java.util.*;

/**
 * Created by Ulrik on 31-May-17.
 */
public class ChessGame {
    private ChessBoard chessBoard;
    private Player player1;
    private Player player2;
    private HashMap<Player,PieceColor> selectedPieceColor;
    private Player playersTurn;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
        chessBoard.initialize();
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.selectedPieceColor = new HashMap<Player, PieceColor>();
        this.selectedPieceColor.put(player1,PieceColor.WHITE);
        this.selectedPieceColor.put(player2,PieceColor.BLACK);
        this.playersTurn = player1;
    }

    public ChessBoard getChessBoard() {
        return this.chessBoard;
    }

    public Player getPlayersTurn() {
        return playersTurn;
    }

    public Player getNotPlayersTurn() {
        if (playersTurn==player1)   return player2;
        else                        return player1;
    }

    public void switchPlayersTurn() {
        if (playersTurn == player1) playersTurn = player2;
        else                        playersTurn = player1;
    }

    public boolean checkIfGameIsOver() {
        PieceColor color    = selectedPieceColor.get(playersTurn);
        boolean isCheckMate = chessBoard.isCheckMate(color);
        boolean isDraw      = false; //TODO: chessBoard.isDraw(color);
        return isCheckMate || isDraw;
    }

    public PieceColor getCurrentPlayerColor() {
        return selectedPieceColor.get(playersTurn);
    }

    public PieceColor getOppositePlayerColor() {
        Player oppositePlayer = getNotPlayersTurn();
        return selectedPieceColor.get(oppositePlayer);
    }
}
