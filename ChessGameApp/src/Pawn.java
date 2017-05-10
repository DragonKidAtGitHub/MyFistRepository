/**
 * Created by ujo on 06.04.2017.
 */
public class Pawn extends Piece {

    Pawn(Color color){
        this.color = color;
    }



    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))           return false;
        else if (isOutOfBoundsMove(toX, toY))           return false;
        else if (isDiagonalMove(fromX, fromY, toX,toY)) return true;
        else return isNormalPawnMove(fromX, fromY, toX, toY);
    }

    private boolean isDiagonalMove(int fromX, int fromY, int toX, int toY){
        if (color==Color.BLACK)     return (toX == fromX + 1 && toY == fromY + 1) || (toX == fromX + 1 && toY == fromY - 1);
        else                        return (toX == fromX - 1 && toY == fromY + 1) || (toX == fromX - 1 && toY == fromY - 1);
    }

    private boolean isNormalPawnMove(int fromX, int fromY, int toX, int toY) {
        if (color==Color.BLACK)     return (toX == fromX + 1 && toY == fromY) || (!hasMoved && toX == fromX + 2 && toY == fromY);
        else                        return (toX == fromX - 1 && toY == fromY) || (!hasMoved && toX == fromX - 2 && toY == fromY);
    }

}
