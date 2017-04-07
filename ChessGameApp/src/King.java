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
    public boolean isValidMove(int toX, int toY) {
        if (toX == x && toY == y){ // No move
            return false;
        }
        else if (toX < 0 || toX > 7 || toY < 0 || toY > 7){// Out of bounds
            return false;
        }
        else if (Math.sqrt(Math.pow(Math.abs(x - toX), 2) + Math.pow(Math.abs(y - toY), 2)) <= Math.sqrt(2)){
            return true;
        }
        else{
            return false;
        }
    }
}
