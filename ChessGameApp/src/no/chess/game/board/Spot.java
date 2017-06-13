package no.chess.game.board;
import no.chess.game.piece.Piece;

/**
 * Created by ujo on 06.04.2017.
 */
public class Spot {
    private Piece piece;
    private SpotColor color;

    Spot(SpotColor color) {
        this.color = color;
        this.piece = null;
    }

    public Spot(Piece piece, SpotColor color) {
        this.piece = piece;
        this.color = color;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }

    Piece removePiece() {
        Piece p = getPiece();
        this.piece = null;
        return p;
    }

}
