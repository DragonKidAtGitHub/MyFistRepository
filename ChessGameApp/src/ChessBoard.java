/**
 * Created by ujo on 06.04.2017.
 */
public class ChessBoard {
    private final int rows = 8;
    private final int columns = 8;
    private Spot[][] spots = new Spot[rows][columns];

    ChessBoard() {
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                boolean rowIsEven = ((row % 2) == 0);
                boolean columnIsEven = ((column % 2) == 0);
                if (rowIsEven){
                    if (columnIsEven)   spots[row][column] = new Spot(Color.WHITE);
                    else                spots[row][column] = new Spot(Color.BLACK);
                }
                else {
                    if (columnIsEven)   spots[row][column] = new Spot(Color.BLACK);
                    else                spots[row][column] = new Spot(Color.WHITE);
                }
            }
        }
    }

    void initialize() {
        Color color = Color.BLACK;
        spots[0][0].setPiece(new Rook(color));
        spots[0][1].setPiece(new Knight(color));
        spots[0][2].setPiece(new Bishop(color));
        spots[0][3].setPiece(new Queen(color));
        spots[0][4].setPiece(new King(color));
        spots[0][5].setPiece(new Bishop(color));
        spots[0][6].setPiece(new Knight(color));
        spots[0][7].setPiece(new Rook(color));
        for (int column = 0; column < columns; column++){
            spots[1][column].setPiece(new Pawn(color));
        }

        color = Color.WHITE;
        spots[7][0].setPiece(new Rook(color));
        spots[7][1].setPiece(new Knight(color));
        spots[7][2].setPiece(new Bishop(color));
        spots[7][3].setPiece(new Queen(color));
        spots[7][4].setPiece(new King(color));
        spots[7][5].setPiece(new Bishop(color));
        spots[7][6].setPiece(new Knight(color));
        spots[7][7].setPiece(new Rook(color));
        for (int column = 0; column < columns; column++){
            spots[6][column].setPiece(new Pawn(color));
        }
    }

    private void setPiece(Piece p, int x, int y){
        spots[x][y].setPiece(p);
    }

    Piece getPiece(int x, int y){
        return spots[x][y].getPiece();
    }

    public void movePiece(int fromX, int fromY, int toX, int toY){
        Piece p = getPiece(fromX,fromY);
        setPiece(p,toX,toY);
    }

    public void removePiece(int x, int y) {
        spots[x][y].removePiece();
    }
}
