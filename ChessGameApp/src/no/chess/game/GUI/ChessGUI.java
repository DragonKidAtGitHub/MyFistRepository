package no.chess.game.GUI;

import no.chess.game.ChessGame;
import no.chess.game.board.ChessBoard;
import no.chess.game.board.Spot;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Ulrik on 14-Jun-17.
 */
public class ChessGUI{
    private JFrame mainGUIFrame;
    private BoardPanel boardPanel;
    private ChessGame chessGame;

    static final Dimension OUTER_FRAME_DIMENSION    = new Dimension(600,600);
    static final Dimension BOARD_PANEL_DIMENSION    = new Dimension(400,350);
    static final Dimension SPOT_PANEL_DIMENSION     = new Dimension(10,10);

    ChessGUI() {
        this.chessGame = new ChessGame();
        this.mainGUIFrame = new JFrame("Chess game");
        this.mainGUIFrame.setSize(OUTER_FRAME_DIMENSION);
        JMenuBar menuBar = createMenuBar();
        this.boardPanel = new BoardPanel(this.chessGame);
        this.mainGUIFrame.add(boardPanel,BorderLayout.CENTER);
        this.mainGUIFrame.setJMenuBar(menuBar);
        this.setWindowListener();
        this.mainGUIFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        menuBar.add(fileMenu);
        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem openFile = new JMenuItem("Open file");
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open the file you need");
            }
        });
        fileMenu.add(openFile);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    private void setWindowListener() {
        this.mainGUIFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        ChessGUI app = new ChessGUI();
    }
}
