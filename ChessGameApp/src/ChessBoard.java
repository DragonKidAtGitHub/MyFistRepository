import java.util.ArrayList;
import java.util.List;

/**
 * Created by ujo on 06.04.2017.
 */
public class ChessBoard {
    private final int rows = 8;
    private final int columns = 8;
    private Spot[][] spots = new Spot[rows][columns];
    private ArrayList<Piece> removedPieces = new ArrayList<Piece>();

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

    public void movePiece(int fromX, int fromY, int toX, int toY, Color color){
        if (isOwnPiece(fromX,fromY,color)) {
            boolean toSpotIsEmpty       = isEmpty(toX,toY);
            boolean toSpotIsEnemy       = isEnemyPiece(toX,toY,color);
            boolean isValidMove         = getPiece(fromX,fromY).isValidMove(fromX,fromY,toX,toY);
            boolean isNoPieceBetween    = !isPieceBetween(fromX,fromY,toX,toY);
            if (isValidMove && isNoPieceBetween) {
                if (toSpotIsEmpty) {
                    Piece p = removePiece(fromX, fromY);
                    boolean firstTimeMoved = !p.hasMoved();
                    if (firstTimeMoved) p.setHasMoved();
                    setPiece(toX, toY, p);
                }
                else if (toSpotIsEnemy) {
                    Piece temp = getPiece(fromX,fromY);
                    boolean isOkayToTakeEnemy = true;
                    if (temp instanceof Pawn) isOkayToTakeEnemy = ((Pawn) temp).isDiagonalMove(fromX,fromY,toX,toY);
                    if (isOkayToTakeEnemy) {
                        Piece p = removePiece(fromX, fromY);
                        boolean firstTimeMoved = !p.hasMoved();
                        if (firstTimeMoved) p.setHasMoved();
                        removedPieces.add(getPiece(toX,toY));
                        setPiece(toX, toY, p);
                    }
                }
            }
        }
    }

    public Piece removePiece(int x, int y) {
        return spots[x][y].removePiece();
    }

    public void setPiece(int x, int y, Piece p) {
        spots[x][y].setPiece(p);
    }

    public boolean isEmpty(int x, int y) {
        return spots[x][y].getPiece() == null;
    }

    public boolean isOwnPiece(int x, int y, Color color) {
        return (!isEmpty(x, y) && spots[x][y].getPiece().getColor() == color);
    }

    public boolean isEnemyPiece(int x, int y, Color ownColor) {
        Piece p = spots[x][y].getPiece();
        if (isEmpty(x,y))  return false;
        Color enemyColor;
        if (ownColor == Color.BLACK)    enemyColor = Color.WHITE;
        else                            enemyColor = Color.BLACK;
        return spots[x][y].getPiece().getColor() == enemyColor;
    }

    public boolean isPieceBetween(int fromX, int fromY, int toX, int toY) {
        Piece p = getPiece(fromX,fromY);
        if (p instanceof Knight) {
            return false;
        }
        else {
            if ((fromX == toX) || (fromY == toY)) { // straight move
                if ((fromX == toX) && (toY > fromY)) { // straight right move
                    for (int i = 1; i < Math.abs(toY-fromY); i++) {
                        if (!isEmpty(fromX, fromY+i)) return true;
                    }
                }
                else if ((fromX == toX) && (toY < fromY)) { // straight left move
                    for (int i = 1; i < Math.abs(toY-fromY); i++) {
                        if (!isEmpty(fromX, fromY-i)) return true;
                    }
                }
                else if ((toX > fromX) && (fromY == toY)) { // straight down move
                    for (int i = 1; i < Math.abs(toX-fromX); i++) {
                        if (!isEmpty(fromX+i, fromY)) return true;
                    }
                }
                else if ((toX < fromX) && (fromY == toY)) { // straight up move
                    for (int i = 1; i < Math.abs(toX-fromX); i++) {
                        if (!isEmpty(fromX-i, fromY)) return true;
                    }
                }
            }
            else if (Math.abs(toX-fromX) == Math.abs(toY-fromY)) { //diagonal move
                if ((fromX < toX) && (fromY < toY)) { //diagonal down-right move
                    for (int i = 1; i < Math.abs(fromX-toX); i++) {
                        if (!isEmpty(fromX+i,fromY+i)) return true;
                    }
                }
                else if ((fromX < toX) && (fromY > toY)) { //diagonal down-left move
                    for (int i = 1; i < Math.abs(fromX-toX); i++) {
                        if (!isEmpty(fromX+i,fromY-i)) return true;
                    }
                }
                else if ((fromX > toX) && (fromY < toY)) { //diagonal up-left move
                    for (int i = 1; i < Math.abs(fromX-toX); i++) {
                        if (!isEmpty(fromX-i,fromY+i)) return true;
                    }
                }
                else if ((fromX > toX) && (fromY > toY)) { //diagonal up-right move
                    for (int i = 1; i < Math.abs(fromX-toX); i++) {
                        if (!isEmpty(fromX-i,fromY-i)) return true;
                    }
                }
            }
        }
        return false;
    }

    public void printBoardLayout() {
        String row = "";
        for (int x = 0; x < rows; x++){
            for (int y = 0; y < columns; y++) {
                Piece p = spots[x][y].getPiece();
                if (p != null) {
                    String c;
                    if (p.getColor() == Color.BLACK)    c = "b";
                    else                                c = "w";
                    row += c;
                    if (p instanceof Rook)              row += "R";
                    else if (p instanceof Knight)       row += "k";
                    else if (p instanceof Bishop)       row += "B";
                    else if (p instanceof Queen)        row += "Q";
                    else if (p instanceof King)         row += "K";
                    else if (p instanceof Pawn)         row += "P";
                }
                else row += "__";
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
                if (p != null) {
                    String c;
                    if (p.getColor() == Color.BLACK)    c = "b";
                    else                                c = "w";
                    if (p instanceof Rook)              array[x][y] = c + "R";
                    else if (p instanceof Knight)       array[x][y] = c + "k";
                    else if (p instanceof Bishop)       array[x][y] = c + "B";
                    else if (p instanceof Queen)        array[x][y] = c + "Q";
                    else if (p instanceof King)         array[x][y] = c + "K";
                    else if (p instanceof Pawn)         array[x][y] = c + "P";
                }
                else array[x][y] = "  ";
            }

        }
        return array;
    }

    static void print2DArray(String[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        String row = "";
        for (int x = 0; x < rows; x++){
            for (int y = 0; y < columns; y++) {
                String c = array[x][y];
                if (c != "  ")  row += c;
                else            row += "__";
                row += " ";
            }
            row += "\n";
        }
        System.out.print(row);
    }


}
