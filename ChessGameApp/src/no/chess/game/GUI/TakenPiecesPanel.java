package no.chess.game.GUI;

import no.chess.game.board.ChessBoard;
import no.chess.game.piece.Piece;
import no.chess.game.piece.PieceColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ulrik on 23-Jun-17.
 */
public class TakenPiecesPanel extends JPanel {

    private JPanel northPanel;
    private JPanel southPanel;

    private static Color PANEL_COLOR = Color.lightGray;
    private static Dimension TAKEN_PIECES_DIMENSION = new Dimension(70,560);

    public TakenPiecesPanel() {
        super(new BorderLayout());
        this.setBackground(PANEL_COLOR);
        this.northPanel = new JPanel(new GridLayout(0,2));
        this.southPanel = new JPanel(new GridLayout(0,2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel.setBackground(PANEL_COLOR);
        this.add(northPanel,BorderLayout.NORTH);
        this.add(southPanel,BorderLayout.SOUTH);
        this.setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void updatePanel(ChessBoard chessBoard) {
        this.northPanel.removeAll();
        this.southPanel.removeAll();
        ArrayList<Piece> removedPiecesList = chessBoard.getRemovedPieces();
        for (Piece piece : removedPiecesList) {
            if (piece != null) {
                if (piece.getColor() == PieceColor.BLACK)       this.northPanel.add(makeIconLabel(piece));
                else if (piece.getColor() == PieceColor.WHITE)  this.southPanel.add(makeIconLabel(piece));
            }
        }
        this.revalidate();
    }

    public JLabel makeIconLabel(Piece piece) {
        String defaultImagePath = "ChessGameApp/art/simple/";
        String imageColorString = piece.getColor().colorString();
        String pieceTypeString = piece.getType();
        try {
            BufferedImage image = ImageIO.read(new File(defaultImagePath + imageColorString + pieceTypeString + ".gif"));
            return new JLabel(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
