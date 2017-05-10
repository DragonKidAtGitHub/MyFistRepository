import java.util.List;

/**
 * Created by ujo on 06.04.2017.
 */
public abstract class Piece {
    Color color;
    boolean hasMoved;

    public abstract boolean isValidMove(int fromX, int fromY, int toX, int toY);

    Color getColor() {
        return this.color;
    }

    void setHasMoved() {
        this.hasMoved = true;
    }

    boolean hasMoved() {
        return this.hasMoved;
    }

    boolean isNoMove(int fromX, int fromY, int toX, int toY) {
        return (toX==fromX && toY==fromY);
    }

    boolean isOutOfBoundsMove(int toX, int toY) {
        return (toX < 0 || toX > 7 || toY < 0 || toY > 7);
    }
}
