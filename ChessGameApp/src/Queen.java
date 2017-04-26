/**
 * Created by ujo on 21.04.2017.
 */
public class Queen implements Piece {
    private Color color;

    public Queen(Color color){
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))               return false;
        else if (isOutOfBoundsMove(toX, toY))               return false;
        else if (isDiagonalMove(fromX, fromY, toX, toY))    return true;
        else return isStraightMove(fromX, fromY, toX, toY);
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

    private boolean isStraightMove(int fromX, int fromY, int toX, int toY){
        return (toX==fromX || toY ==fromY);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
