/**
 * Created by ujo on 21.04.2017.
 */
public class Rook extends Piece {

    Rook(Color color){
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX,toY))                return false;
        else if (isOutOfBoundsMove(toX,toY))                return false;
        else return isStraightMove(fromX, fromY, toX, toY);
    }

    private boolean isStraightMove(int fromX, int fromY, int toX, int toY){
        return (toX==fromX || toY ==fromY);
    }

}
