import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ujo on 06.04.2017.
 */
public class ChessBoard {
    public static final int rows = 8;
    public static final int columns = 8;
    private Spot[][] spots = new Spot[rows][columns];
    private ArrayList<Piece> removedPieces = new ArrayList<Piece>();
    private ArrayList<String[][]> listOfLayouts = new ArrayList<String[][]>();

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
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column<columns; column++) {
                spots[row][column].removePiece();
            }
        }

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
        if (!isOutOfBounds(x,y))    return spots[x][y].getPiece();
        else                        return null;
    }

    public ArrayList<Piece> getRemovedPieces() {
        return removedPieces;
    }

    boolean movePiece(int fromX, int fromY, int toX, int toY, Color color){
        boolean pieceIsMoved = false;
        if (isOwnPiece(fromX,fromY,color)) {
            Piece p = getPiece(fromX, fromY);
            boolean toSpotIsEmpty       = isEmpty(toX,toY);
            boolean toSpotIsAnEnemy     = isEnemyPiece(toX,toY,color);
            boolean isCastlingMove      = isCastlingMove(fromX,fromY,toX,toY,color);
            boolean isEnPassantMove     = isEnPassantMove(fromX,fromY,toX,toY,color);
            boolean isLegalMove         = isLegalMove(fromX,fromY,toX,toY,color);
            boolean isPromoted          = p.checkIsPromoted(fromX,fromY,toX,toY);
            boolean isPawnDoubleMove    = p.isSpecialFirstMove(fromX,fromY,toX,toY);

            setEnPassantStates(color);

            if (isCastlingMove)                         performCastlingMove(fromX,fromY,toX,toY);
            else if (isEnPassantMove)                   performEnPassantMove(fromX,fromY,toX,toY);
            else if (isLegalMove && toSpotIsEmpty)      gotoSpot(fromX,fromY,toX,toY);
            else if (isLegalMove && toSpotIsAnEnemy)    captureSpot(fromX,fromY,toX,toY);

            if (isLegalMove && isPromoted)              promotePiece(toX,toY,color);
            if (isLegalMove && isPawnDoubleMove)        p.setEnPassantPossible();

            pieceIsMoved = isCastlingMove || isEnPassantMove || (isLegalMove && toSpotIsEmpty) || (isLegalMove && toSpotIsAnEnemy);
        }

        return pieceIsMoved;
    }

    private void setEnPassantStates(Color c) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (isOwnPiece(row,column,c)) {
                    Piece p = getPiece(row,column);
                    p.setEnPassantState();
                }
            }
        }
    }

    private void promotePiece(int x, int y, Color color) {
        Piece p = getPiece(x,y);
        if (p != null && !isOutOfBounds(x,y)) spots[x][y].setPiece(new Queen(color));
    }

    Piece removePiece(int x, int y) {
        return spots[x][y].removePiece();
    }

    void setPiece(int x, int y, Piece p) {
        if (!isOutOfBounds(x,y)) spots[x][y].setPiece(p);
    }

    private boolean isEmpty(int x, int y) {
        return spots[x][y].getPiece() == null;
    }

    boolean isOwnPiece(int x, int y, Color color) {
        return (!isEmpty(x,y) && spots[x][y].getPiece().getColor() == color);
    }

    private boolean isEnemyPiece(int x, int y, Color ownColor) {
        Piece p = spots[x][y].getPiece();
        if (isEmpty(x,y))  return false;
        Color enemyColor;
        if (ownColor == Color.BLACK)    enemyColor = Color.WHITE;
        else                            enemyColor = Color.BLACK;
        return spots[x][y].getPiece().getColor() == enemyColor;
    }

    boolean isPieceBetween(int fromX, int fromY, int toX, int toY) {
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

    void printBoardLayout() {
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

    String[][] boardLayout() {
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

    private boolean isCastlingMove(int fromX, int fromY, int toX, int toY, Color color) {
        Piece p = getPiece(fromX,fromY);
        if (p == null) return false;
        if (p.isCastlingMove(fromX,fromY,toX,toY)) {
            boolean isRightCastling             = (toY == 6);
            boolean isLeftCastling              = (toY == 2);
            boolean rookReadyToCastle           = false;
            boolean noPiecesBetweenKingAndRook  = false;
            boolean isInCheck                   = false;
            boolean moveThroughCheckSpot        = false;
            boolean endPositionIsInCheck        = false;
            if (isRightCastling) {
                Piece r = getPiece(fromX, 7);
                if (r != null) {
                    rookReadyToCastle = r.isCastlingMove(fromX, 7, toX, 5);
                    noPiecesBetweenKingAndRook = ((getPiece(fromX, 5) == null) && (getPiece(fromX, 6) == null));
                    isInCheck = isChecked(color);
                    moveThroughCheckSpot = isCheckedAfterMove(fromX, fromY, fromX, 5, color);
                    endPositionIsInCheck = isCheckedAfterMove(fromX, fromY, fromX, 6, color);
                }
            } else if (isLeftCastling) {
                Piece r = getPiece(fromX, 0);
                if (r != null) {
                    rookReadyToCastle = r.isCastlingMove(fromX, 0, toX, 3);
                    noPiecesBetweenKingAndRook = ((getPiece(fromX, 1) == null) && (getPiece(fromX, 2) == null) && (getPiece(fromX, 3) == null));
                    isInCheck = isChecked(color);
                    moveThroughCheckSpot = isCheckedAfterMove(fromX, fromY, fromX, 3, color);
                    endPositionIsInCheck = isCheckedAfterMove(fromX, fromY, fromX, 2, color);
                }
            }
            return (rookReadyToCastle && noPiecesBetweenKingAndRook && !isInCheck && !moveThroughCheckSpot && !endPositionIsInCheck);
        }
        else return false;
    }

    private boolean isEnPassantMove(int fromX, int fromY, int toX, int toY, Color color) {
        Piece ownPiece                  = getPiece(fromX,fromY);
        Piece enemyPiece                = (color==Color.WHITE) ? getPiece(toX+1,toY) : getPiece(toX-1,toY);
        boolean isValidMove             = ownPiece.isValidMove(fromX,fromY,toX,toY);
        boolean isNoPieceBetween        = !isPieceBetween(fromX,fromY,toX,toY);
        boolean isNotExposingCheck      = !isCheckedAfterMove(fromX,fromY,toX,toY,color);
        boolean isOnFifthRank           = ((color==Color.WHITE) && (fromX==3)) || ((color==Color.BLACK) && (fromX==4));
        boolean enemyIsInEnPassantState = (enemyPiece!=null) ? enemyPiece.checkIfEnPassantIsPossible() : false;

        return (isValidMove && isNoPieceBetween && isNotExposingCheck && isOnFifthRank && enemyIsInEnPassantState);
    }

    boolean isChecked(Color kingColor) {
        return kingCanBeCaptured(kingColor);
    }

    boolean isCheckMate(Color kingColor) {
        if (!isChecked(kingColor)) return false;
        else {
            boolean isPossibleToMoveOutOfCheck    = possibleToMoveKingOutOfCheck(kingColor);
            boolean isPossibleToTakeAttacker      = possibleToTakeKingAttackerNotUsingKing(kingColor);
            boolean isPossibleToBlockCheck        = possibleToBlockCheckNotUsingKing(kingColor);
            return (!isPossibleToMoveOutOfCheck && !isPossibleToBlockCheck && !isPossibleToTakeAttacker);
        }
    }

    boolean isStaleMate(Color kingColor) {
        boolean isCheck = isChecked(kingColor);
        boolean possibleToMoveKingAway = possibleToMoveKingOutOfCheck(kingColor);
        if (isCheck || possibleToMoveKingAway) return false;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (canMoveToWithoutKing(row,column,kingColor)) return false;
            }
        }
        return true;
    }

    boolean isCheckedAfterMove(int fromX, int fromY, int toX, int toY, Color color) {
        if (!isOutOfBounds(toX,toY)) {
            Piece fromPiece = getPiece(fromX,fromY);
            Piece toPiece = getPiece(toX, toY);
            forceMovePiece(fromX, fromY, toX, toY);
            boolean isChecked = isChecked(color);
            setPiece(fromX, fromY, fromPiece);
            setPiece(toX, toY, toPiece);
            return isChecked;
        }
        else return true;
    }

    private void performCastlingMove(int fromX, int fromY, int toX, int toY) {
        captureSpot(fromX,fromY,toX,toY);
        boolean isRightCastling     = (toY == 6);
        boolean isLeftCastling      = (toY == 2);
        if (isRightCastling)        captureSpot(fromX,7,fromX,5);
        else if (isLeftCastling)    captureSpot(fromX,0,fromX,3);
    }

    private void performEnPassantMove(int fromX, int fromY, int toX, int toY) {
        gotoSpot(fromX,fromY,toX,toY);
        Piece p = null;
        if (toX<(rows/2))   p = removePiece(toX+1,toY);
        else                p = removePiece(toX-1,toY);
        if (p != null) removedPieces.add(p);
    }

    private void forceMovePiece(int fromX, int fromY, int toX, int toY){
        if (!isOutOfBounds(fromX,fromY) && !isOutOfBounds(toX,toY)) {
            Piece p = removePiece(fromX, fromY);
            setPiece(toX, toY, p);
        }
    }

    private boolean kingCanBeCaptured(Color kingColor) {
        ArrayList<Position> kingAttackerList = findKingAttackers(kingColor);
        return !kingAttackerList.isEmpty();
    }

    boolean possibleToMoveKingOutOfCheck(Color kingColor) {
        Position kingPos = findKing(kingColor);
        if (kingPos != null) {
            int x = kingPos.getX();
            int y = kingPos.getY();
            if (     isLegalMove(x,y,x+1,y+1,kingColor) && !isCheckedAfterMove(x,y,x+1,y+1,kingColor))     return true;
            else if (isLegalMove(x,y,x+1,y+0,kingColor) && !isCheckedAfterMove(x,y,x+1,y+0,kingColor))     return true;
            else if (isLegalMove(x,y,x+1,y-1,kingColor) && !isCheckedAfterMove(x,y,x+1,y-1,kingColor))     return true;
            else if (isLegalMove(x,y,x+0,y-1,kingColor) && !isCheckedAfterMove(x,y,x+0,y-1,kingColor))     return true;
            else if (isLegalMove(x,y,x-1,y-1,kingColor) && !isCheckedAfterMove(x,y,x-1,y-1,kingColor))     return true;
            else if (isLegalMove(x,y,x-1,y+0,kingColor) && !isCheckedAfterMove(x,y,x-1,y+0,kingColor))     return true;
            else if (isLegalMove(x,y,x-1,y+1,kingColor) && !isCheckedAfterMove(x,y,x-1,y+1,kingColor))     return true;
            else if (isLegalMove(x,y,x+0,x+1,kingColor) && !isCheckedAfterMove(x,y,x+0,x+1,kingColor))     return true;
            else return false;
        }
        else return false;
    }

    boolean possibleToTakeKingAttackerNotUsingKing(Color kingColor) {
        ArrayList<Position> attackerPositionList = findKingAttackers(kingColor);
        Iterator<Position> itr = attackerPositionList.iterator();
        while (itr.hasNext()) {
            Position attackerPos = itr.next();
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    Piece p = getPiece(row, column);
                    if (isOwnPiece(row, column, kingColor) && !(p instanceof King)) {
                        boolean isValidMove             = p.isValidMove(row, column, attackerPos.getX(), attackerPos.getY());
                        boolean isNoPieceBetween        = !isPieceBetween(row, column, attackerPos.getX(), attackerPos.getY());
                        boolean isOkayToCapture         = p.isOkayToCapture(row, column, attackerPos.getX(), attackerPos.getY());
                        if (isValidMove && isNoPieceBetween && isOkayToCapture) return true;
                    }
                }
            }
        }
        return false;
    }

    boolean possibleToBlockCheckNotUsingKing(Color kingColor) {
        ArrayList<Position> attackerList = findKingAttackers(kingColor);
        Position kingPos = findKing(kingColor);
        int kingPosX = kingPos.getX();
        int kingPosY = kingPos.getY();
        Iterator<Position> itr = attackerList.iterator();
        while (itr.hasNext()) {
            Position attackerPos = itr.next();
            int attackerPosX = attackerPos.getX();
            int attackerPosY = attackerPos.getY();
            Piece attackerPiece = getPiece(attackerPosX,attackerPosY);
            boolean attackerIsNotKnight = !(attackerPiece instanceof Knight);
            if (attackerIsNotKnight) {
                if ((attackerPosX == kingPosX) || (attackerPosY == kingPosY)) { // straight attack move
                    if ((attackerPosX == kingPosX) && (attackerPosY > kingPosY)) { // straight right attack move
                        for (int i = 1; i < Math.abs(attackerPosY-kingPosY); i++) {
                            if (canMoveToWithoutKing(attackerPosX, kingPosY+i, kingColor)) return true;
                        }
                    }
                    else if ((attackerPosX == kingPosX) && (kingPosY < attackerPosY)) { // straight left attack move
                        for (int i = 1; i < Math.abs(kingPosY-attackerPosY); i++) {
                            if (canMoveToWithoutKing(attackerPosX, attackerPosY-i, kingColor)) return true;
                        }
                    }
                    else if ((kingPosX > attackerPosX) && (attackerPosY == kingPosY)) { // straight down attack move
                        for (int i = 1; i < Math.abs(kingPosX-attackerPosX); i++) {
                            if (canMoveToWithoutKing(attackerPosX+i, attackerPosY, kingColor)) return true;
                        }
                    }
                    else if ((kingPosX < attackerPosX) && (attackerPosY == kingPosY)) { // straight up attack move
                        for (int i = 1; i < Math.abs(kingPosX-attackerPosX); i++) {
                            if (canMoveToWithoutKing(attackerPosX-i, attackerPosY, kingColor)) return true;
                        }
                    }
                }
                else if (Math.abs(kingPosX-attackerPosX) == Math.abs(kingPosY-attackerPosY)) { //diagonal attack move
                    if ((attackerPosX < kingPosX) && (attackerPosY < kingPosY)) { //diagonal down-right attack move
                        for (int i = 1; i < Math.abs(attackerPosX-kingPosX); i++) {
                            if (canMoveToWithoutKing(attackerPosX+i,attackerPosY+i, kingColor)) return true;
                        }
                    }
                    else if ((attackerPosX < kingPosX) && (attackerPosY > kingPosY)) { //diagonal down-left attack move
                        for (int i = 1; i < Math.abs(attackerPosX-kingPosX); i++) {
                            if (canMoveToWithoutKing(attackerPosX+i,attackerPosY-i, kingColor)) return true;
                        }
                    }
                    else if ((attackerPosX > kingPosX) && (attackerPosY < kingPosY)) { //diagonal up-left attack move
                        for (int i = 1; i < Math.abs(attackerPosX-kingPosX); i++) {
                            if (canMoveToWithoutKing(attackerPosX-i,attackerPosY+i, kingColor)) return true;
                        }
                    }
                    else if ((attackerPosX > kingPosX) && (attackerPosY > kingPosY)) { //diagonal up-right attack move
                        for (int i = 1; i < Math.abs(attackerPosX-kingPosX); i++) {
                            if (canMoveToWithoutKing(attackerPosX-i,attackerPosY-i, kingColor)) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isLegalMove(int fromX, int fromY, int toX, int toY, Color color) {
        Piece p = getPiece(fromX,fromY);
        boolean isValidMove         = p.isValidMove(fromX, fromY, toX, toY);
        boolean isNoPieceBetween    = !isPieceBetween(fromX, fromY, toX, toY);
        boolean isNotExposingCheck  = !isCheckedAfterMove(fromX, fromY, toX, toY, color);
        boolean isLegalMove         = isValidMove && isNoPieceBetween && isNotExposingCheck;

        if (isLegalMove) {
            boolean toSpotIsEmpty   = isEmpty(toX,toY);
            boolean toSpotIsAnEnemy = isEnemyPiece(toX,toY,color);
            if (toSpotIsEmpty && p.isOkayToMoveWithoutCapturing(fromX,fromY,toX,toY))   return true;
            else if (toSpotIsAnEnemy && p.isOkayToCapture(fromX,fromY,toX,toY))         return true;
        }
        return false;
    }

    private boolean canMoveTo(int toX, int toY, Color c) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (isOwnPiece(row, column, c)) {
                    if (isLegalMove(row, column, toX, toY, c)) return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveToWithoutKing(int toX, int toY, Color c) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Piece p = getPiece(row,column);
                if (isOwnPiece(row, column, c) && !(p instanceof King)) {
                    if (isLegalMove(row, column, toX, toY, c)) return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Position> findKingAttackers(Color kingColor) {
        ArrayList<Position> attackerPositionList = new ArrayList<>();
        Position kingPos = findKing(kingColor);
        if (kingPos != null) {
            int x = kingPos.getX();
            int y = kingPos.getY();
            Color attackerColor = Color.BLACK;
            if (kingColor == Color.BLACK) attackerColor = Color.WHITE;
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    if (isOwnPiece(row, column, attackerColor)) {
                        Piece p = getPiece(row, column);
                        boolean isValidMove = p.isValidMove(row, column, x, y);
                        boolean isNoPieceBetween = !isPieceBetween(row, column, x, y);
                        boolean isOkayToCapture = p.isOkayToCapture(row, column, x, y);
                        Position attackerPos = new Position(row, column);
                        if (isValidMove && isNoPieceBetween && isOkayToCapture) attackerPositionList.add(attackerPos);
                    }
                }
            }
        }
        return attackerPositionList;
    }

    private Position findKing(Color c) {
        Position pos = null;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Piece p = getPiece(row,column);
                if (p instanceof King && c == p.getColor()) {
                    pos = new Position(row,column);
                    break;
                }
            }
        }
        return pos;
    }

    private void captureSpot(int fromX, int fromY, int toX, int toY) {
        Piece p = removePiece(fromX, fromY);
        boolean firstTimeMoved = !p.hasMoved();
        if (firstTimeMoved) p.setHasMoved();
        removedPieces.add(getPiece(toX,toY));
        setPiece(toX, toY, p);
        listOfLayouts.clear();
        listOfLayouts.add(boardLayout());
    }

    private void gotoSpot(int fromX, int fromY, int toX, int toY) {
        Piece p = removePiece(fromX, fromY);
        boolean firstTimeMoved = !p.hasMoved();
        if (firstTimeMoved) p.setHasMoved();
        setPiece(toX, toY, p);
        if (!p.isReversibleMove()) listOfLayouts.clear();
        listOfLayouts.add(boardLayout());
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

    static boolean isOutOfBounds(int x, int y) {
        return (x<0 || x>=rows || y<0 || y>=columns);
    }

}
