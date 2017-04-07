/**
 * Created by ujo on 06.04.2017.
 */
public interface Piece {
    boolean isValidMove(int toX, int toY);
    Color getColor();
    int getX();
    int getY();
}
