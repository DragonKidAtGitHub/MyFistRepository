/**
 * Created by ujo on 06.04.2017.
 */
public class Pawn implements Piece {
    private Color color;
    private int x;
    private int y;
    private boolean isAvailable;

    public Pawn(Color color, int x){
        this.color = color;
        this.x = x;
        if (color == Color.BLACK){
            y = 1;
        }
        else {
            y = 6;
        }
    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        return false;
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
