/**
 * Created by ujo on 06.04.2017.
 */
public class King extends Piece {

    King(Color color) {
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))   return false;
        else if (isOutOfBoundsMove(toX, toY))   return false;
        else return isNormalKingMove(fromX, fromY, toX, toY);
    }

    private boolean isNormalKingMove(int fromX, int fromY, int toX, int toY) {
        return Math.abs(fromX - toX) <= 1 && Math.abs(fromY - toY) <= 1;
    }
}
