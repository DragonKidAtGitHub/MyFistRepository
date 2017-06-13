package no.chess.game.piece;
/**
 * Created by ujo on 06.04.2017.
 */
public class King extends Piece {

    public King(PieceColor color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))           return false;
        else if (isOutOfBoundsMove(toX, toY))           return false;
        else if (isCastlingMove(fromX,fromY,toX,toY))   return true;
        else return isNormalKingMove(fromX, fromY, toX, toY);
    }

    private boolean isNormalKingMove(int fromX, int fromY, int toX, int toY) {
        return Math.abs(fromX - toX) <= 1 && Math.abs(fromY - toY) <= 1;
    }

    @Override
    public boolean isCastlingMove(int fromX, int fromY, int toX, int toY) {
        return (!hasMoved && fromX==toX && Math.abs(fromY-toY) == 2);
    }
}
