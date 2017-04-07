/**
 * Created by ujo on 06.04.2017.
 */
public class Spot {
    private Piece piece;
    private Color color;

    public Spot(Color color) {
        this.color = color;
    }

    public Spot(Piece piece, Color color) {
        this.piece = piece;
        this.color = color;
    }
}
