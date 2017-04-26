/**
 * Created by ujo on 06.04.2017.
 */
public class Spot {
    private Piece piece;
    private Color color;

    Spot(Color color) {
        this.color = color;
        this.piece = null;
    }

    public Spot(Piece piece, Color color) {
        this.piece = piece;
        this.color = color;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }

    public void removePiece() {
        this.piece = null;
    }
}
