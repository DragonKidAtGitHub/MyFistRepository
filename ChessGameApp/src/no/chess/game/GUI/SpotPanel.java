package no.chess.game.GUI;

import no.chess.game.ChessGame;
import no.chess.game.board.ChessBoard;
import no.chess.game.board.Position;
import no.chess.game.piece.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static no.chess.game.GUI.ChessGUI.SPOT_PANEL_DIMENSION;

/**
 * Created by Ulrik on 14-Jun-17.
 */
public class SpotPanel extends JPanel {
    private int x;
    private int y;

    private final Color lightSpotColor  = new Color(239,218,176);
    private final Color darkSpotColor   = new Color(179,137,93);
    private final Border yellowBorder   = BorderFactory.createLineBorder(Color.yellow,5);
    private final Border noBorder       = BorderFactory.createEmptyBorder();

    SpotPanel(int x, int y, BoardPanel boardPanel, ChessGame chessGame, ChessGUI chessGUI) {
        super(new GridBagLayout());
        this.x = x;
        this.y = y;
        super.setPreferredSize(SPOT_PANEL_DIMENSION);
        setSpotPanelColor(x,y);
        drawPieceOnSpot(chessGame.getChessBoard());
        super.addMouseListener(createMoseAdapter(boardPanel, chessGame, chessGUI));
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

    private MouseAdapter createMoseAdapter(BoardPanel boardPanel, ChessGame chessGame, ChessGUI chessGUI) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (boardPanel.getSourcePosition()==null && chessGame.getChessBoard().isOwnPiece(x,y,chessGame.getCurrentPlayerColor())) {
                        // Fist click
                        boardPanel.setSourcePosition(new Position(x,y));
                        boardPanel.setMovingPiece(chessGame.getChessBoard().getPiece(x,y));
                        if (boardPanel.getMovingPiece()==null) boardPanel.cancelMove(chessGame,chessGUI);
                    }
                    else if (boardPanel.getSourcePosition()!=null){
                        // Second click
                        boardPanel.setDestinationPosition(new Position(x,y));
                        Position sourcePos  = boardPanel.getSourcePosition();
                        Position destPos    = boardPanel.getDestinationPosition();
                        ChessBoard board    = chessGame.getChessBoard();
                        PieceColor curColor = chessGame.getCurrentPlayerColor();
                        boolean isMoved = board.movePiece(sourcePos,destPos,curColor);
                        if (isMoved) {
                            chessGame.switchPlayersTurn();
                            boardPanel.cancelMove(chessGame,chessGUI);
                            if (chessGame.checkIfGameIsOver()) System.out.println("Game Over");
                        }
                    }
                    boardPanel.drawBoard(chessGame,chessGUI);
                }
                else if (SwingUtilities.isRightMouseButton(e)) {
                    boardPanel.cancelMove(chessGame,chessGUI);
                    System.out.println("Cancel move for " + chessGame.getCurrentPlayerColor().longColorString());
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

    private void highlightSelectedPiece(BoardPanel boardPanel, ChessGame chessGame, ChessGUI chessGUI) {
        if (chessGUI.isHighlightingSelectedPiece()) {
            Piece piece = boardPanel.getMovingPiece();
            if (piece!=null && piece.getColor()==chessGame.getCurrentPlayerColor()) {
                if (boardPanel.getSourcePosition().getX()==this.x && boardPanel.getSourcePosition().getY()==this.y) {
                    super.setBorder(yellowBorder);
                }
            }
            else super.setBorder(noBorder);
        }
        else super.setBorder(noBorder);
    }

    private void highlightLegalMoves(BoardPanel boardPanel, ChessGame chessGame, ChessGUI chessGUI) {
        if (chessGUI.isLegalMoveHighlighted()) {
            for (Position pos : getLegalMovesForPiece(boardPanel, chessGame)) {
                if (pos.getX()==this.x && pos.getY()==this.y) {
                    try {
                        add(new JLabel(new ImageIcon(ImageIO.read(new File("ChessGameApp/art/misc/green_dot.png")))));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private ArrayList<Position> getLegalMovesForPiece(BoardPanel boardPanel, ChessGame chessGame) {
        if (boardPanel.getSourcePosition()!=null) {
            return chessGame.getChessBoard().getLegalMoves(boardPanel.getSourcePosition().getX(),boardPanel.getSourcePosition().getY(),chessGame.getCurrentPlayerColor());
        }
        else return new ArrayList<Position>();
    }

    public void drawSpot(BoardPanel boardPanel, ChessGame chessGame, ChessGUI chessGUI) {
        drawPieceOnSpot(chessGame.getChessBoard());
        highlightLegalMoves(boardPanel,chessGame, chessGUI);
        highlightSelectedPiece(boardPanel,chessGame,chessGUI);
        super.revalidate();
        super.repaint();
    }
}
