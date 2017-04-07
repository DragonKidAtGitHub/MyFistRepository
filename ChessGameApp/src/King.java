/**
 * Created by ujo on 06.04.2017.
 */
public class King implements Piece {
    private Color color;
    private int x;
    private int y;
    private boolean isAvailable;

    public King(Color color) {
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
    public void moveTo(int toX, int toY) {

    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        if (isNoMove(toX, toY)){
            return false;
        }
        else if (isOutOfBoundsMove(toX, toY)){
            return false;
        }
        else if (Math.sqrt(Math.pow(Math.abs(x - toX), 2) + Math.pow(Math.abs(y - toY), 2)) <= Math.sqrt(2)){
            return true;
        }
        else{
            return false;
        }
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
