/**
 * Created by ujo on 21.04.2017.
 */
public class Bishop implements Piece {
    private Color color;
    private int x;
    private int y;

    Bishop(Color color, int x){
        this.color = color;
        this.x = x;
        if (color==Color.BLACK) y = 0;
        else                    y = 7;
    }

    public Bishop(Color color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        if (isNoMove(toX,toY))                  return false;
        else if (isOutOfBoundsMove(toX,toY))    return false;
        else return isDiagonalMove(toX, toY);
    }

    @Override
    public boolean isNoMove(int toX, int toY) {
        return (toX==x && toY==y);
    }

    @Override
    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }

    private boolean isDiagonalMove(int toX, int toY){
        return (Math.abs(x-toX) == Math.abs(y-toY));
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

}
