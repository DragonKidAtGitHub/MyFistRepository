/**
 * Created by ujo on 06.04.2017.
 */
public class King implements Piece {
    private Color color;
    private int x;
    private int y;
    private boolean isAvailable;

    King(Color color) {
        this.color = color;
        x = 4;
        if (color == Color.BLACK){
            y = 0;
        }
        else{
            y = 7;
        }
        isAvailable = true;
    }

    public King(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        isAvailable = true;
    }

    public Color getColor() {
        return color;
    }

    public int getX(){
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void toggleIsAvailable() {
        isAvailable = !isAvailable;
    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        if (isNoMove(toX, toY)){
            return false;
        }
        else if (isOutOfBoundsMove(toX, toY)){
            return false;
        }
        else return Math.abs(x - toX) <= 1 && Math.abs(y - toY) <= 1;
    }

    @Override
    public boolean isNoMove(int toX, int toY) {
        return (toX == x && toY == y);
    }

    @Override
    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }
}
