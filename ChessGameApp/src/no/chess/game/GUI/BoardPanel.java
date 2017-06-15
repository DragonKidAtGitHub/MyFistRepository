package no.chess.game.GUI;

import no.chess.game.board.ChessBoard;

import javax.swing.*;
import java.awt.*;

import static no.chess.game.GUI.ChessGUI.BOARD_PANEL_DIMENSION;
import static no.chess.game.board.ChessBoard.*;

/**
 * Created by Ulrik on 14-Jun-17.
 */
public class BoardPanel extends JPanel {
    private SpotPanel[][] spotPanels;

    public BoardPanel(ChessBoard chessBoard) {
        super(new GridLayout(rows,columns));
        this.spotPanels = new SpotPanel[rows][columns];
        for (int row=0; row<rows; row++) {
            for (int column=0; column<columns; column++) {
                SpotPanel spotPanel = new SpotPanel(row,column,chessBoard);
                this.spotPanels[row][column] = spotPanel;
                super.add(spotPanel);
            }
        }
        super.setPreferredSize(BOARD_PANEL_DIMENSION);
        super.validate();
    }
}
