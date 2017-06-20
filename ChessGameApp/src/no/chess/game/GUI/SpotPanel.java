package no.chess.game.GUI;

import no.chess.game.ChessGame;
import no.chess.game.board.ChessBoard;
import no.chess.game.board.Position;
import no.chess.game.piece.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static no.chess.game.GUI.ChessGUI.SPOT_PANEL_DIMENSION;

/**
 * Created by Ulrik on 14-Jun-17.
 */
public class SpotPanel extends JPanel {
    private int x;
    private int y;

    private final Color lightSpotColor  = new Color(239,218,176);
    private final Color darkSpotColor   = new Color(179,137,93);

    SpotPanel(int x, int y, BoardPanel boardPanel, ChessGame chessGame) {
        super(new GridBagLayout());
        this.x = x;
        this.y = y;
        super.setPreferredSize(SPOT_PANEL_DIMENSION);
        setSpotPanelColor(x,y);
        drawPieceOnSpot(chessGame.getChessBoard());
        super.addMouseListener(createMoseAdapter(boardPanel, chessGame));
        super.validate();
    }

    private void drawPieceOnSpot(ChessBoard chessBoard) {
        this.removeAll();
        if (!chessBoard.isEmpty(this.x,this.y)) {
            String defaultImagePath = "ChessGameApp/art/simple/";
            Piece piece = chessBoard.getPiece(this.x,this.y);
            String imageColorString = piece.getColor().colorString();
            String pieceTypeString = piece.getType();
            try {
                BufferedImage image = ImageIO.read(new File(defaultImagePath+imageColorString+pieceTypeString+".gif"));
                super.add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private MouseAdapter createMoseAdapter(BoardPanel boardPanel, ChessGame chessGame) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {//
                    if (boardPanel.getSourcePosition()==null) {
                        // Fist click
                        System.out.println("First click for " + chessGame.getCurrentPlayerColor().longColorString() + " on (" + x + "," + y +") with left mouse button");
                        boardPanel.setSourcePosition(new Position(x,y));
                        if (chessGame.getChessBoard().isEmpty(x,y)) {
                            System.out.println("Cancel move with left button for " + chessGame.getCurrentPlayerColor().longColorString());
                            boardPanel.cancelMove();
                        }
                    }
                    else {
                        // Second click
                        System.out.println("Second click for " + chessGame.getCurrentPlayerColor().longColorString() + " on (" + x + "," + y +") with left mouse button");
                        boardPanel.setDestinationPosition(new Position(x,y));
                        boolean isMoved = chessGame.getChessBoard().movePiece(boardPanel.getSourcePosition(),boardPanel.getDestinationPosition(),chessGame.getCurrentPlayerColor());
                        if (isMoved) {
                            chessGame.switchPlayersTurn();
                            boardPanel.cancelMove();
                            if (chessGame.checkIfGameIsOver()) System.out.println("Game Over");
                        }
                        else {
                            System.out.println("Move was not performed for " + chessGame.getCurrentPlayerColor().longColorString());
                        }
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            boardPanel.drawBoard(chessGame.getChessBoard());
                        }
                    });
                }
                else if (SwingUtilities.isRightMouseButton(e)) {
                    boardPanel.cancelMove();
                    System.out.println("Cancel move with right mouse button for " + chessGame.getCurrentPlayerColor().longColorString());
                }
            }
        };
        return mouseAdapter;
    }

    private void setSpotPanelColor(int row, int column) {
        boolean rowIsEven = ((row % 2) == 0);
        boolean columnIsEven = ((column % 2) == 0);
        if (rowIsEven){
            if (columnIsEven)   super.setBackground(lightSpotColor);
            else                super.setBackground(darkSpotColor);
        }
        else {
            if (columnIsEven)   super.setBackground(darkSpotColor);
            else                super.setBackground(lightSpotColor);
        }
    }

    public void drawSpot(ChessBoard chessBoard) {
        drawPieceOnSpot(chessBoard);
        super.validate();
        super.repaint();
    }
}
