/**
 * Created by ujo on 21.04.2017.
 */
public class Queen extends Piece {

    Queen(Color color){
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))               return false;
        else if (isOutOfBoundsMove(toX, toY))               return false;
        else if (isDiagonalMove(fromX, fromY, toX, toY))    return true;
        else return isStraightMove(fromX, fromY, toX, toY);
    }

    private boolean isDiagonalMove(int fromX, int fromY, int toX, int toY){
        return (Math.abs(fromX-toX) == Math.abs(fromY-toY));
    }

    private boolean isStraightMove(int fromX, int fromY, int toX, int toY){
        return (toX==fromX || toY ==fromY);
    }
}
