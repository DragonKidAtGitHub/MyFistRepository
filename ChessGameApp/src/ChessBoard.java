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
        for (int x = 0; x < rows; x++){
            for (int y = 0; y < columns; y++) {
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
        String[][] array = new String[rows][columns];
        for (int x = 0; x < rows; x++){
            for (int y = 0; y < columns; y++) {
                Piece p = spots[x][y].getPiece();
                if (p instanceof Rook)          array[x][y] = "R";
                else if (p instanceof Knight)   array[x][y] = "k";
                else if (p instanceof Bishop)   array[x][y] = "B";
                else if (p instanceof Queen)    array[x][y] = "Q";
                else if (p instanceof King)     array[x][y] = "K";
                else if (p instanceof Pawn)     array[x][y] = "p";
                else                            array[x][y] = " ";
            }
        }
        return array;
    }

    static void print2DArray(String[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        String row = "\n";
        for (int x = 0; x < rows; x++){
            for (int y = 0; y < columns; y++) {
                String c = array[x][y];
                if (c != " ")   row += c;
                else            row += "_";
                row += " ";
            }
            row += "\n";
        }
        System.out.print(row);
    }
}
