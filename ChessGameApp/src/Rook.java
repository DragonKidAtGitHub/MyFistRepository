/**
 * Created by ujo on 21.04.2017.
 */
public class Rook implements Piece {
    private Color color;
    private boolean hasMoved;

    Rook(Color color){
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX,toY))                return false;
        else if (isOutOfBoundsMove(toX,toY))                return false;
        else return isStraightMove(fromX, fromY, toX, toY);
    }

    public boolean isNoMove(int fromX, int fromY, int toX, int toY) {
        return (toX==fromX && toY==fromY);
    }

    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }

    private boolean isStraightMove(int fromX, int fromY, int toX, int toY){
        return (toX==fromX || toY ==fromY);
    }

    @Override
    public Color getColor() {
        return color;
    }

}
