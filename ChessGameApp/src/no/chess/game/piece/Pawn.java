package no.chess.game.piece;

/**
 * Created by ujo on 06.04.2017.
 */
public class Pawn extends Piece {
    private boolean enPassantIsPossible = false;

    public Pawn(PieceColor color){
        this.color = color;
        this.hasMoved = false;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))               return false;
        else if (isOutOfBoundsMove(toX, toY))               return false;
        else if (isDiagonalMove(fromX, fromY, toX,toY))     return true;
        else if (isDoubleFirstMove(fromX, fromY, toX, toY)) return true;
        else return isNormalPawnMove(fromX, fromY, toX, toY);
    }

    @Override
    public String getType() {
        return "P";
    }

    @Override
    public boolean isOkayToCapture(int fromX, int fromY, int toX, int toY) {
        return isDiagonalMove(fromX,fromY,toX,toY);
    }

    @Override
    public boolean isOkayToMoveWithoutCapturing(int fromX, int fromY, int toX, int toY) {
        return (isNormalPawnMove(fromX,fromY,toX,toY) || isDoubleFirstMove(fromX,fromY,toX,toY));
    }

    @Override
    public boolean checkIfEnPassantIsPossible() {
        return enPassantIsPossible;
    }

    @Override
    public void setEnPassantPossible() {
        enPassantIsPossible = true;
    }

    @Override
    public void setEnPassantState() {
        if (hasMoved && enPassantIsPossible)  enPassantIsPossible = false;
    }

    @Override
    public boolean isSpecialFirstMove(int fromX, int fromY, int toX, int toY) {
        return isDoubleFirstMove(fromX,fromY,toX,toY);
    }

    @Override
    public boolean checkIsPromoted(int x, int y) {
        if (color== PieceColor.WHITE)   return (x==0);
        else                            return (x==7);
    }

    @Override
    public boolean isReversibleMove() {
        return false;
    }

    private boolean isDiagonalMove(int fromX, int fromY, int toX, int toY){
        if (color== PieceColor.BLACK)     return (toX == fromX + 1 && toY == fromY + 1) || (toX == fromX + 1 && toY == fromY - 1);
        else                        return (toX == fromX - 1 && toY == fromY + 1) || (toX == fromX - 1 && toY == fromY - 1);
    }

    private boolean isNormalPawnMove(int fromX, int fromY, int toX, int toY) {
        if (color== PieceColor.BLACK) return (toX == fromX + 1 && toY == fromY);
        else                    return (toX == fromX - 1 && toY == fromY);
    }

    private boolean isDoubleFirstMove(int fromX, int fromY, int toX, int toY) {
        if (color== PieceColor.BLACK) return (!hasMoved && toX==fromX+2 && toY==fromY);
        else                    return (!hasMoved && toX==fromX-2 && toY==fromY);
    }

}
