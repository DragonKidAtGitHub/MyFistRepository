package no.chess.game.board;

/**
 * Created by Ulrik Jorgensen on 18-May-17.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPosition() {
        return this;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
