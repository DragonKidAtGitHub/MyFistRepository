/**
 * Created by ujo on 06.04.2017.
 */
public class Pawn implements Piece {
    private Color color;
    private boolean hasMoved;

    public Pawn(Color color){
        this.color = color;
    }

    public boolean isNoMove(int fromX, int fromY, int toX, int toY) {
        return (toX==fromX && toY==fromY);
    }

    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }

    @Override
    public Color getColor() {
        return color;
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
