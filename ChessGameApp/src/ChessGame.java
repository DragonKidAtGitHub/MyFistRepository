import no.chess.game.board.*;
import no.chess.game.piece.*;

import java.util.*;

/**
 * Created by Ulrik on 31-May-17.
 */
public class ChessGame {
    private ChessBoard cb;
    private Player player1;
    private Player player2;
    private HashMap<Player,PieceColor> selectedPieceColor;
    private boolean gameIsSetup = false;
    private Player playersTurn;

    ChessGame() {
    }

    public void setupGame() {
        setupPlayers();
        chooseColors();
        setupChessBoard();
        gameIsSetup = true;
    }

    public void setupPlayers() {
        System.out.println("Choose name of Player 1: ");
        Scanner scan = new Scanner(System.in);
        String player1Name = scan.nextLine();
        System.out.println("Choose name of Player 2: ");
        String player2Name = scan.nextLine();
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
    }

    public void chooseColors() {
        selectedPieceColor = new HashMap<Player, PieceColor>();
        Scanner scan = new Scanner(System.in);
        boolean isOkayInput = false;
        System.out.println("Choose color for Player 1 (" + player1.getName() + "): White(W)/Black(B): ");
        String player1Color = scan.nextLine();
        isOkayInput = (player1Color.equalsIgnoreCase("W")) || (player1Color.equalsIgnoreCase("B"));
        while (!isOkayInput) {
            System.out.println("Choose color for Player 1 (" + player1.getName() + "): White(W)/Black(B): ");
            player1Color = scan.nextLine();
            isOkayInput = (player1Color.equalsIgnoreCase("W")) || (player1Color.equalsIgnoreCase("B"));
        }
        if (player1Color.equalsIgnoreCase("W")) {
            selectedPieceColor.put(player1, PieceColor.WHITE);
            selectedPieceColor.put(player2, PieceColor.BLACK);
            playersTurn = player1;
        }
        else {
            selectedPieceColor.put(player1, PieceColor.BLACK);
            selectedPieceColor.put(player2, PieceColor.WHITE);
            playersTurn = player2;
        }
        System.out.println("Player 1 ("+ player1.getName()+ ") has chosen " + selectedPieceColor.get(player1).toString() + " pieces.");
        System.out.println("Player 2 ("+ player2.getName()+ ") has chosen " + selectedPieceColor.get(player2).toString() + " pieces.");
    }

    public void setupChessBoard() {
        cb = new ChessBoard();
        cb.initialize();
    }

    public void playTurn() {
        System.out.println("\nCurrent layout:");
        cb.printBoardLayout();
        performMove();
    }

    public void playGame() {
        if (!gameIsSetup) setupGame();
        boolean gameIsOver = false;
        do {
            playTurn();
            switchPlayersTurn();
            gameIsOver = checkIfGameIsOver();

        }
        while (!gameIsOver);
        finishGame();
    }

    private void finishGame() {
        boolean isCheckMate = cb.isCheckMate(selectedPieceColor.get(playersTurn));
        boolean isStaleMate = cb.isStaleMate(selectedPieceColor.get(playersTurn));
        System.out.println("Game Over!");
        switchPlayersTurn();
        if (isCheckMate) {
            System.out.println(playersTurn.getName() + " is the winner!");
            playersTurn.increaseWinNumber();
            switchPlayersTurn();
            playersTurn.increaseLossNumber();
            switchPlayersTurn();
        }
        else if (isStaleMate) {
            System.out.println("The game is a draw.");
            playersTurn.increaseDrawNumber();
            switchPlayersTurn();
            playersTurn.increaseDrawNumber();
        }
    }

    public void performMove() {
        int[] inputs = new int[4];
        boolean isOkayMove = false;
        boolean pieceIsMoved = false;
        System.out.println(playersTurn.getName()+ ": Choose piece to move by typing from-spot and to-spot in the following format (fromX fromY toX toY).\nNote: (0,0), (0,7), (7,0) and (7,7) is upper-left, upper-right, lower-left and lower-right corner, correspondingly.");
        while (!isOkayMove) {
            List<Integer> userInput = new ArrayList<Integer>();
            Scanner scan = new Scanner(System.in);
            String moveLine = scan.nextLine();
            Scanner s = new Scanner(moveLine).useDelimiter("\\s");
            while (s.hasNextInt()) {
                userInput.add(s.nextInt());
            }
            if (userInput.size()==4) {
                System.out.println("Chosen move: (" + userInput.get(0) + "," +userInput.get(1) + ")->(" + userInput.get(2) +"," +userInput.get(3) + ")");
                pieceIsMoved = cb.movePiece(userInput.get(0),userInput.get(1),userInput.get(2),userInput.get(3),selectedPieceColor.get(playersTurn));
                isOkayMove = isOkayMoveInput(inputs) && pieceIsMoved;
            }
            if (!isOkayMove) System.out.println("Input not accepted. Try again.");
        }
    }

    public void switchPlayersTurn() {
        if (playersTurn == player1) playersTurn = player2;
        else                        playersTurn = player1;
    }

    public boolean isOkayMoveInput(int[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] < 0 || inputs[i] >= ChessBoard.columns) return false;
        }
        return true;
    }

    public boolean checkIfGameIsOver() {
        PieceColor color = selectedPieceColor.get(playersTurn);
        boolean isCheckMate = cb.isCheckMate(color);
        boolean isDraw = cb.isDraw(color);
        return isCheckMate || isDraw;
    }
}
