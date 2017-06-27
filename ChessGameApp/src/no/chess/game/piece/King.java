package no.chess.game.piece;
/**
 * Created by ujo on 06.04.2017.
 */
public class King extends Piece {

    public King(PieceColor color) {
        this.color      = color;
        this.hasMoved   = false;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX,fromY,toX,toY))                  return false;
        else if (isOutOfBoundsMove(toX,toY))                return false;
        else if (isCastlingAttempt(fromX,fromY,toX,toY))    return true;
        else return isNormalKingMove(fromX,fromY,toX,toY);
    }

    @Override
    public String getType() {
        return "K";
    }

    private boolean isNormalKingMove(int fromX, int fromY, int toX, int toY) {
        return Math.abs(fromX - toX) <= 1 && Math.abs(fromY - toY) <= 1;
    }

    @Override
    public boolean isCastlingAttempt(int fromX, int fromY, int toX, int toY) {
        return (!hasMoved && fromX==toX && Math.abs(fromY-toY) == 2);
    }
}
