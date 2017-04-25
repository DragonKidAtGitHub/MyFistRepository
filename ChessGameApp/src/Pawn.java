/**
 * Created by ujo on 06.04.2017.
 */
public class Pawn implements Piece {
    private Color color;
    private int x;
    private int y;
    private boolean hasMoved;
    private boolean isAvailable;

    Pawn(Color color, int x){
        this.color = color;
        isAvailable = true;
        hasMoved = false;
        if (color == Color.BLACK)   y = 1;
        else                        y = 6;
        if (x >= 0 && x <= 7)       this.x = x;
        else                        this.x = -1;
    }

    public Pawn(Color color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
        isAvailable = true;
    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        if (isNoMove(toX, toY))                 return false;
        else if (isOutOfBoundsMove(toX, toY))   return false;
        else if (isDiagonalMove(toX,toY))       return true;
        else{
            if (color==Color.BLACK) {
                return toX == x && toY == y + 1 || !hasMoved && toX == x && toY == y + 2;
            }
            else if (color == Color.WHITE) {
                return toX == x && toY == y - 1 || !hasMoved && toX == x && toY == y - 2;
            }
            else return false;
        }
    }

    @Override
    public boolean isNoMove(int toX, int toY) {
        return (toX==x && toY==y);
    }

    @Override
    public boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
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
    public void toggleIsAvailable() {
        isAvailable = !isAvailable;
    }

    private boolean isDiagonalMove(int toX, int toY){
        if (color==Color.BLACK)     return toX == x + 1 && toY == y + 1 || toX == x - 1 && toY == y + 1;
        else                        return toX == x + 1 && toY == y - 1 || toX == x - 1 && toY == y - 1;
    }

    public void checkHasMoved() {

    }
}
