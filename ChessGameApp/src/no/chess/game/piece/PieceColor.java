package no.chess.game.piece;

/**
 * Created by ujo on 06.04.2017.
 */
public enum PieceColor {
    BLACK, WHITE;

    public String colorString() {
        switch (this) {
            case BLACK: return "B";
            case WHITE: return "W";
            default:    throw new IllegalArgumentException();
        }
    }

    public String longColorString() {
        switch (this) {
            case BLACK: return "Black";
            case WHITE: return "White";
            default:    throw new IllegalArgumentException();
        }
    }
}
