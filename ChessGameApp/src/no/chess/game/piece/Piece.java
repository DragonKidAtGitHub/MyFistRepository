package no.chess.game.piece;
import no.chess.game.board.ChessBoard;

/**
 * Created by ujo on 06.04.2017.
 */
public abstract class Piece {
    PieceColor color;
    boolean hasMoved;

    public abstract boolean isValidMove(int fromX, int fromY, int toX, int toY);

    public boolean isCastlingMove(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    public boolean isOkayToCapture(int fromX, int fromY, int toX, int toY) {
        return true;
    }

    public boolean isOkayToMoveWithoutCapturing(int fromX, int fromY, int toX, int toY) {
        return true;
    }

    public boolean checkIfEnPassantIsPossible() {
        return false;
    }

    public void setEnPassantPossible() {}

    public void setEnPassantState() {}

    public boolean isSpecialFirstMove(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    public boolean checkIsPromoted(int fromX, int fromY, int toX, int toY) {
        return false;
    }

    public boolean isReversibleMove() {
        return true;
    }

    public PieceColor getColor() {
        return this.color;
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    boolean isNoMove(int fromX, int fromY, int toX, int toY) {
        return (toX==fromX && toY==fromY);
    }

    boolean isOutOfBoundsMove(int toX, int toY) {
        return ChessBoard.isOutOfBounds(toX,toY);
    }
}
