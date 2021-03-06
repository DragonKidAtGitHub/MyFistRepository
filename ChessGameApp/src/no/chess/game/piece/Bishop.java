package no.chess.game.piece;
/**
 * Created by ujo on 21.04.2017.
 */
public class Bishop extends Piece {

    public Bishop(PieceColor color){
        this.color      = color;
        this.hasMoved   = false;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX,fromY,toX,toY))                  return false;
        else if (isOutOfBoundsMove(toX,toY))                return false;
        else return isDiagonalMove(fromX,fromY,toX, toY);
    }

    @Override
    public String getType() {
        return "B";
    }

    public boolean isNoMove(int fromX, int fromY, int toX, int toY) {
        return (toX==fromX && toY==fromY);
    }

    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }

    private boolean isDiagonalMove(int fromX, int fromY, int toX, int toY){
        return (Math.abs(fromX-toX) == Math.abs(fromY-toY));
    }


}
