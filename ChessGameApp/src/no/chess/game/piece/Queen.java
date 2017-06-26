package no.chess.game.piece;

/**
 * Created by ujo on 21.04.2017.
 */
public class Queen extends Piece {

    public Queen(PieceColor color){
        this.color = color;
        this.hasMoved = false;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))               return false;
        else if (isOutOfBoundsMove(toX, toY))               return false;
        else if (isDiagonalMove(fromX, fromY, toX, toY))    return true;
        else return isStraightMove(fromX, fromY, toX, toY);
    }

    @Override
    public String getType() {
        return "Q";
    }

    private boolean isDiagonalMove(int fromX, int fromY, int toX, int toY){
        return (Math.abs(fromX-toX) == Math.abs(fromY-toY));
    }

    private boolean isStraightMove(int fromX, int fromY, int toX, int toY){
        return (toX==fromX || toY ==fromY);
    }
}
