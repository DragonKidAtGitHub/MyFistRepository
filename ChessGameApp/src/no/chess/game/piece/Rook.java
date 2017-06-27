package no.chess.game.piece;

/**
 * Created by ujo on 21.04.2017.
 */
public class Rook extends Piece {

    public Rook(PieceColor color){
        this.color      = color;
        this.hasMoved   = false;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX,toY))                return false;
        else if (isOutOfBoundsMove(toX,toY))                return false;
        else if (isCastlingAttempt(fromX,fromY,toX,toY))       return true;
        else return isStraightMove(fromX, fromY, toX, toY);
    }

    @Override
    public String getType() {
        return "R";
    }

    private boolean isStraightMove(int fromX, int fromY, int toX, int toY){
        return (toX==fromX || toY ==fromY);
    }

    @Override
    public boolean isCastlingMove(int fromX, int fromY, int toX, int toY) {
        return  (!hasMoved && fromX==toX && ((fromY==0 && toY==3) || (fromY==7 && toY==5)));
    }
}
