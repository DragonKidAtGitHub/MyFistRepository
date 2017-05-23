/**
 * Created by ujo on 06.04.2017.
 */
public class Pawn extends Piece {
    boolean possibleEnPassant;

    Pawn(Color color){
        this.color = color;
        possibleEnPassant = false;
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
    boolean isOkayToCapture(int fromX, int fromY, int toX, int toY) {
        return isDiagonalMove(fromX,fromY,toX,toY);
    }

    @Override
    boolean possibleEnPassant(int fromX, int fromY, int toX, int toY) {
        return  (isDoubleFirstMove(fromX, fromY, toX, toY));
    }

    protected boolean isDiagonalMove(int fromX, int fromY, int toX, int toY){
        if (color==Color.BLACK)     return (toX == fromX + 1 && toY == fromY + 1) || (toX == fromX + 1 && toY == fromY - 1);
        else                        return (toX == fromX - 1 && toY == fromY + 1) || (toX == fromX - 1 && toY == fromY - 1);
    }

    private boolean isNormalPawnMove(int fromX, int fromY, int toX, int toY) {
        if (color==Color.BLACK) return (toX == fromX + 1 && toY == fromY);
        else                    return (toX == fromX - 1 && toY == fromY);
    }

    private boolean isDoubleFirstMove(int fromX, int fromY, int toX, int toY) {
        if (color==Color.BLACK) return (!hasMoved && toX==fromX+2 && toY==fromY);
        else                    return (!hasMoved && toX==fromX-2 && toY==fromY);
    }

}
