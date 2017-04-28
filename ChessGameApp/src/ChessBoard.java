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
        spots[1][0].setPiece(new Knight(color));
        spots[2][0].setPiece(new Bishop(color));
        spots[3][0].setPiece(new Queen(color));
        spots[4][0].setPiece(new King(color));
        spots[5][0].setPiece(new Bishop(color));
        spots[6][0].setPiece(new Knight(color));
        spots[7][0].setPiece(new Rook(color));
        for (int column = 0; column < columns; column++){
            spots[column][1].setPiece(new Pawn(color));
        }

        color = Color.WHITE;
        spots[0][7].setPiece(new Rook(color));
        spots[1][7].setPiece(new Knight(color));
        spots[2][7].setPiece(new Bishop(color));
        spots[3][7].setPiece(new Queen(color));
        spots[4][7].setPiece(new King(color));
        spots[5][7].setPiece(new Bishop(color));
        spots[6][7].setPiece(new Knight(color));
        spots[7][7].setPiece(new Rook(color));
        for (int column = 0; column < columns; column++){
            spots[column][6].setPiece(new Pawn(color));
        }
    }

    Piece getPiece(int x, int y){
        return spots[x][y].getPiece();
    }

    public void movePiece(int fromX, int fromY, int toX, int toY){
        Piece movingPiece = spots[fromX][fromY].getPiece();
        if (movingPiece != null) {
            Piece occupiedPiece = spots[toX][toY].getPiece();
            Boolean toSpotIsEmpty = (occupiedPiece == null);
            Boolean isSameColor = true;
            if (occupiedPiece != null) isSameColor = (movingPiece.getColor() == occupiedPiece.getColor());
            Boolean isValidPieceMove = movingPiece.isValidMove(fromX, fromY, toX, toY);
            if (isValidPieceMove && (!isSameColor || toSpotIsEmpty)) {
                spots[toX][toY].setPiece(movingPiece);
                spots[fromX][fromY].removePiece();
            }
        }
    }

    public void removePiece(int x, int y) {
        spots[x][y].removePiece();
    }

    public void printBoardLayout() {
        String row = "";
        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++) {
                Piece p = spots[x][y].getPiece();
                if (p instanceof Rook)          row += "R";
                else if (p instanceof Knight)   row += "k";
                else if (p instanceof Bishop)   row += "B";
                else if (p instanceof Queen)    row += "Q";
                else if (p instanceof King)     row += "K";
                else if (p instanceof Pawn)     row += "p";
                else                            row += "_";
                row += " ";
            }
            row += "\n";
        }
        System.out.print(row);
    }

    public String[][] boardLayout() {
        String[][] chessBord = new String[rows][columns];
        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++) {
                Piece p = spots[x][y].getPiece();
                if (p instanceof Rook)          chessBord[y][x] = "R";
                else if (p instanceof Knight)   chessBord[y][x] = "k";
                else if (p instanceof Bishop)   chessBord[y][x] = "B";
                else if (p instanceof Queen)    chessBord[y][x] = "Q";
                else if (p instanceof King)     chessBord[y][x] = "K";
                else if (p instanceof Pawn)     chessBord[y][x] = "p";
                else                            chessBord[y][x] = " ";
            }
        }
        return chessBord;
    }

    static void print2DArray(String[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        String row = "\n";
        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++) {
                String c = array[y][x];
                if (c != " ")   row += c;
                else            row += "_";
                row += " ";
            }
            row += "\n";
        }
        System.out.print(row);
    }
}
