/**
 * Created by ujo on 21.04.2017.
 */
public class Knight implements Piece {
    private Color color;

    Knight(Color color){
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))   return false;
        else if (isOutOfBoundsMove(toX, toY))   return false;
        else return isKnightMove(fromX, fromY, toX, toY);
    }

    public boolean isNoMove(int fromX, int fromY, int toX, int toY) {
        return (toX==fromX && toY==fromY);
    }

    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }

    private boolean isKnightMove(int fromX, int fromY, int toX, int toY){
        return ((Math.abs(fromX-toX)==1 && Math.abs(fromY-toY)==2) || (Math.abs(fromX-toX)==2 && Math.abs(fromY-toY)==1));
    }

    @Override
    public Color getColor() {
        return color;
    }
}

