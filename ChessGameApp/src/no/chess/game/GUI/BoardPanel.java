package no.chess.game.GUI;

import no.chess.game.ChessGame;
import no.chess.game.board.ChessBoard;
import no.chess.game.board.Position;
import no.chess.game.piece.Piece;

import javax.swing.*;
import java.awt.*;

import static no.chess.game.GUI.ChessGUI.BOARD_PANEL_DIMENSION;
import static no.chess.game.board.ChessBoard.*;

/**
 * Created by Ulrik on 14-Jun-17.
 */
public class BoardPanel extends JPanel {
    private SpotPanel[][] spotPanels;

    private Position sourcePosition         = null;
    private Position destinationPosition    = null;
    private Piece movingPiece               = null;

    public BoardPanel(ChessGame chessGame, ChessGUI chessGUI) {
        super(new GridLayout(rows,columns));
        this.spotPanels = new SpotPanel[rows][columns];
        for (int row=0; row<rows; row++) {
            for (int column=0; column<columns; column++) {
                SpotPanel spotPanel = new SpotPanel(row,column,this,chessGame,chessGUI);
                this.spotPanels[row][column] = spotPanel;
                super.add(spotPanel);
            }
        }
        super.setPreferredSize(BOARD_PANEL_DIMENSION);
        super.validate();
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getDestinationPosition() {
        return destinationPosition;
    }

    public Piece getMovingPiece() {
        return this.movingPiece;
    }

    public void setSourcePosition(Position sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public void setDestinationPosition(Position destinationPosition) {
        this.destinationPosition = destinationPosition;
    }

    public void setMovingPiece(Piece piece) {
        this.movingPiece = piece;
    }

    public void cancelMove() {
        this.sourcePosition         = null;
        this.destinationPosition    = null;
        this.movingPiece            = null;
    }

    public void drawBoard(ChessGame chessGame, ChessGUI chessGUI) {
        super.removeAll();
        for (int row=0; row<rows; row++) {
            for (int column=0; column<columns; column++) {
                this.spotPanels[row][column].drawSpot(this,chessGame,chessGUI);
                super.add(this.spotPanels[row][column]);
            }
        }
        super.validate();
        super.repaint();
    }
}
