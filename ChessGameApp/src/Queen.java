/**
 * Created by ujo on 21.04.2017.
 */
public class Queen implements Piece {
    private Color color;
    private int x;
    private int y;
    private boolean isAvailable;

    public Queen(Color color){
        this.color = color;
        isAvailable = true;
        x = 3;
        if (color==Color.BLACK) y = 0;
        else                    y = 7;
    }

    public Queen(Color color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
        isAvailable = true;
    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        if (isNoMove(toX,toY))                  return false;
        else if (isOutOfBoundsMove(toX,toY))    return false;
        else if (isDiagonalMove(toX,toY))       return true;
        else if (isStraightMove(toX,toY))       return true;
        else                                    return false;
    }

    @Override
    public boolean isNoMove(int toX, int toY) {
        return (toX==x && toY==y);
    }

    @Override
    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }

    public boolean isDiagonalMove(int toX, int toY){
        return (Math.abs(x-toX) == Math.abs(y-toY));
    }

    public boolean isStraightMove(int toX, int toY){
        return (toX==x || toY ==y);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void moveTo(int toX, int toY) {
        this.x = x;
        this.y = y;
    }
}
