package no.chess.game.piece;

/**
 * Created by ujo on 21.04.2017.
 */
public class Knight extends Piece {

    public Knight(PieceColor color){
        this.color = color;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if (isNoMove(fromX, fromY, toX, toY))   return false;
        else if (isOutOfBoundsMove(toX, toY))   return false;
        else return isKnightMove(fromX, fromY, toX, toY);
    }

    @Override
    public String getType() {
        return "N";
    }

    private boolean isKnightMove(int fromX, int fromY, int toX, int toY){
        return ((Math.abs(fromX-toX)==1 && Math.abs(fromY-toY)==2) || (Math.abs(fromX-toX)==2 && Math.abs(fromY-toY)==1));
    }

}

