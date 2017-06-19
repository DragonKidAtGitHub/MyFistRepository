package no.chess.game.GUI;

import no.chess.game.board.ChessBoard;
import no.chess.game.board.Position;

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

    public BoardPanel(ChessBoard chessBoard) {
        super(new GridLayout(rows,columns));
        this.spotPanels = new SpotPanel[rows][columns];
        for (int row=0; row<rows; row++) {
            for (int column=0; column<columns; column++) {
                SpotPanel spotPanel = new SpotPanel(row,column,chessBoard,this);
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

    public void setSourcePosition(Position sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public void setDestinationPosition(Position destinationPosition) {
        this.destinationPosition = destinationPosition;
    }

    public void cancelMove() {
        this.sourcePosition = null;
        this.destinationPosition = null;
    }

    public void drawBoard(ChessBoard chessBoard) {
        super.removeAll();
        for (int row=0; row<rows; row++) {
            for (int column=0; column<columns; column++) {
                this.spotPanels[row][column].drawSpot(chessBoard);
                super.add(this.spotPanels[row][column]);
            }
        }
        super.validate();
        super.repaint();
    }
}
