import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChessGameTest {
    @Test
    public void testColor() throws Exception {
        Color c1, c2;
        c1 = Color.WHITE;
        c2 = Color.BLACK;

        assertEquals(Color.WHITE, c1);
        assertEquals(Color.BLACK, c2);
    }

    @Test
    public void testMakeKing() throws Exception {
        King p1 = new King(Color.BLACK);
        King p2 = new King(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(4, p1.getX());
        assertEquals(0, p1.getY());
        assertEquals(4, p2.getX());
        assertEquals(7, p2.getY());
    }

    @Test
    public void  testPossibleKingMoves() throws Exception {
        King p = new King(Color.BLACK);
        assertFalse(p.isValidMove(p.getX(),p.getY())); // No move
        assertTrue(p.isValidMove(p.getX()+1,p.getY()+1)); //Diagonal move
        assertFalse(p.isValidMove(p.getX(),p.getY()-1)); // Out of bounds move
        assertFalse(p.isValidMove(p.getX()-2,p.getY())); //Too long x-dir move
        assertFalse(p.isValidMove(p.getX(),p.getY()+2)); //Too long y-dir move
    }

    @Test
    public void testMakePawn() throws Exception {
        Pawn p1 = new Pawn(Color.BLACK, 1);
        Pawn p2 = new Pawn(Color.WHITE, 2);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(1, p1.getY());
        assertEquals(1, p1.getX());
        assertEquals(2, p2.getX());
        assertEquals(6, p2.getY());

        Piece p3 = new Pawn(Color.BLACK, -2);
        assertEquals(-1, p3.getX());
    }

    @Test
    public void testPossibleMovePawn() throws Exception {
        Piece p1 = new Pawn(Color.BLACK, 2);
        Piece p2 = new Pawn(Color.WHITE, 2);
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()));       // No move
        assertFalse(p1.isValidMove(-1, p1.getY()));         // Out of bounds
        assertFalse(p1.isValidMove(8, p1.getY()));          // Out of bounds
        assertFalse(p1.isValidMove(p1.getX(), -1));         // Out of bounds
        assertFalse(p1.isValidMove(-1, 8));             // Out of bounds
        assertTrue(p1.isValidMove(p1.getX(),p1.getY()+1));  // Normal move
        assertTrue(p2.isValidMove(p2.getX(),p2.getY()-1));  // Normal move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()-1)); // Wrong direction move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()+1)); // Wrong direction move
        assertTrue(p1.isValidMove(p1.getX(), p1.getY()+2)); // Allowed first time
        assertTrue(p2.isValidMove(p2.getX(), p2.getY()-2)); // Allowed first time
        assertTrue(p1.isValidMove(p1.getX()+1,p1.getY()+1)); // Diagonal move
        assertTrue(p1.isValidMove(p1.getX()-1,p1.getY()+1)); // Diagonal move
        assertTrue(p2.isValidMove(p2.getX()+1,p2.getY()-1)); // Diagonal move
        assertTrue(p2.isValidMove(p2.getX()-1,p2.getY()-1)); // Diagonal move
        assertFalse(p1.isValidMove(p1.getX()+1,p1.getY()-1)); // Diagonal move
        assertFalse(p1.isValidMove(p1.getX()-1,p1.getY()-1)); // Diagonal move
        assertFalse(p2.isValidMove(p2.getX()+1,p2.getY()+1)); // Diagonal move
        assertFalse(p2.isValidMove(p2.getX()-1,p2.getY()+1)); // Diagonal move
    }

    @Test
    public void testMakeQueen() throws Exception {
        Piece p1 = new Queen(Color.BLACK);
        Piece p2 = new Queen(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(0, p1.getY());
        assertEquals(3, p1.getX());
        assertEquals(3, p2.getX());
        assertEquals(7, p2.getY());
    }

    @Test
    public void  testPossibleQueenMoves() throws Exception {
        Piece p1 = new Queen(Color.BLACK);
        Piece p2 = new Queen(Color.WHITE);
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()));               // No move
        assertTrue(p1.isValidMove(p1.getX()+1,p1.getY()+1));    //Diagonal move
        assertTrue(p1.isValidMove(p1.getX()-3,p1.getY()+3));    //Diagonal move
        assertTrue(p1.isValidMove(p1.getX()+4,p1.getY()+4));    //Diagonal move
        assertTrue(p1.isValidMove(p1.getX()+4,p1.getY()));          //Straight move
        assertTrue(p1.isValidMove(p1.getX(),p1.getY()+3));          //Straight move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()-1));         // Out of bounds move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()+8));         // Out of bounds move

        assertFalse(p2.isValidMove(p2.getX(),p2.getY()));               // No move
        assertTrue(p2.isValidMove(p2.getX()+1,p2.getY()-1));    //Diagonal move
        assertTrue(p2.isValidMove(p2.getX()-3,p2.getY()-3));    //Diagonal move
        assertTrue(p2.isValidMove(p2.getX()+4,p2.getY()-4));    //Diagonal move
        assertTrue(p2.isValidMove(p2.getX()+4,p2.getY()));          //Straight move
        assertTrue(p2.isValidMove(p2.getX(),p2.getY()-3));          //Straight move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()+1));         // Out of bounds move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()-8));         // Out of bounds move
    }

    @Test
    public void testMakeRook() throws Exception {
        Piece p1 = new Rook(Color.BLACK,0);
        Piece p2 = new Rook(Color.WHITE,7);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(0, p1.getY());
        assertEquals(0, p1.getX());
        assertEquals(7, p2.getX());
        assertEquals(7, p2.getY());
    }

    @Test
    public void  testPossibleRookMoves() throws Exception {
        Piece p1 = new Rook(Color.BLACK,0);
        Piece p2 = new Rook(Color.WHITE,7);
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()));               // No move
        assertFalse(p1.isValidMove(p1.getX()+1,p1.getY()+1));    //Diagonal move
        assertFalse(p1.isValidMove(p1.getX()-3,p1.getY()+3));    //Diagonal move
        assertTrue(p1.isValidMove(p1.getX()+4,p1.getY()));          //Straight move
        assertTrue(p1.isValidMove(p1.getX(),p1.getY()+3));          //Straight move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()-1));         // Out of bounds move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()+8));         // Out of bounds move

        assertFalse(p2.isValidMove(p2.getX(),p2.getY()));               // No move
        assertFalse(p2.isValidMove(p2.getX()+1,p2.getY()-1));    //Diagonal move
        assertFalse(p2.isValidMove(p2.getX()-3,p2.getY()-3));    //Diagonal move
        assertTrue(p2.isValidMove(p2.getX()-4,p2.getY()));          //Straight move
        assertTrue(p2.isValidMove(p2.getX(),p2.getY()-3));          //Straight move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()+1));         // Out of bounds move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()-8));         // Out of bounds move
    }

    @Test
    public void testMakeBishop() throws Exception {
        Piece p1 = new Bishop(Color.BLACK,2);
        Piece p2 = new Bishop(Color.WHITE,5);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(0, p1.getY());
        assertEquals(2, p1.getX());
        assertEquals(5, p2.getX());
        assertEquals(7, p2.getY());
    }

    @Test
    public void  testPossibleBishopMoves() throws Exception {
        Piece p1 = new Bishop(Color.BLACK,2);
        Piece p2 = new Bishop(Color.WHITE,5);
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()));               // No move
        assertTrue(p1.isValidMove(p1.getX()+1,p1.getY()+1));    //Diagonal move
        assertTrue(p1.isValidMove(p1.getX()-2,p1.getY()+2));    //Diagonal move
        assertFalse(p1.isValidMove(p1.getX()+4,p1.getY()));          //Straight move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()+3));          //Straight move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()-1));         // Out of bounds move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()+8));         // Out of bounds move

        assertFalse(p2.isValidMove(p2.getX(),p2.getY()));               // No move
        assertTrue(p2.isValidMove(p2.getX()+1,p2.getY()-1));    //Diagonal move
        assertTrue(p2.isValidMove(p2.getX()-3,p2.getY()-3));    //Diagonal move
        assertFalse(p2.isValidMove(p2.getX()-4,p2.getY()));          //Straight move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()-3));          //Straight move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()+1));         // Out of bounds move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()-8));         // Out of bounds move
    }

    @Test
    public void testMakeKnight() throws Exception {
        Piece p1 = new Knight(Color.BLACK,1);
        Piece p2 = new Knight(Color.WHITE,6);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(0, p1.getY());
        assertEquals(1, p1.getX());
        assertEquals(6, p2.getX());
        assertEquals(7, p2.getY());
    }

    @Test
    public void  testPossibleKnightMoves() throws Exception {
        Piece p1 = new Knight(Color.BLACK,1);
        Piece p2 = new Knight(Color.WHITE,5);
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()));                   // No move
        assertFalse(p1.isValidMove(p1.getX()+1,p1.getY()+1));       //Diagonal move
        assertFalse(p1.isValidMove(p1.getX()-2,p1.getY()+2));       //Diagonal move
        assertFalse(p1.isValidMove(p1.getX()+4,p1.getY()));             //Straight move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()+3));             //Straight move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()-1));             // Out of bounds move
        assertFalse(p1.isValidMove(p1.getX(),p1.getY()+8));             // Out of bounds move
        assertTrue(p1.isValidMove(p1.getX()+1,p1.getY()+2));        // Knight move
        assertTrue(p1.isValidMove(p1.getX()-1,p1.getY()+2));        // Knight move
        assertTrue(p1.isValidMove(p1.getX()+2,p1.getY()+1));        // Knight move

        assertFalse(p2.isValidMove(p2.getX(),p2.getY()));                   // No move
        assertFalse(p2.isValidMove(p2.getX()+1,p2.getY()-1));       //Diagonal move
        assertFalse(p2.isValidMove(p2.getX()-3,p2.getY()-3));       //Diagonal move
        assertFalse(p2.isValidMove(p2.getX()-4,p2.getY()));             //Straight move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()-3));             //Straight move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()+1));             // Out of bounds move
        assertFalse(p2.isValidMove(p2.getX(),p2.getY()-8));             // Out of bounds move
        assertTrue(p2.isValidMove(p2.getX()+1,p2.getY()-2));        // Knight move
        assertTrue(p2.isValidMove(p2.getX()-1,p2.getY()-2));        // Knight move
        assertTrue(p2.isValidMove(p2.getX()+2,p2.getY()-1));        // Knight move
    }

    @Test
    public void testInitializeChessBoard() throws Exception {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();

        assertTrue(chessBoard.getPiece(0,0) instanceof Rook);
        assertTrue(chessBoard.getPiece(0,1) instanceof Knight);
        assertTrue(chessBoard.getPiece(0,2) instanceof Bishop);
        assertTrue(chessBoard.getPiece(0,3) instanceof Queen);
        assertTrue(chessBoard.getPiece(0,4) instanceof King);
        assertTrue(chessBoard.getPiece(0,5) instanceof Bishop);
        assertTrue(chessBoard.getPiece(0,6) instanceof Knight);
        assertTrue(chessBoard.getPiece(0,7) instanceof Rook);
        assertTrue(chessBoard.getPiece(1,0) instanceof Pawn);
        assertTrue(chessBoard.getPiece(1,1) instanceof Pawn);
        assertTrue(chessBoard.getPiece(1,2) instanceof Pawn);
        assertTrue(chessBoard.getPiece(1,3) instanceof Pawn);
        assertTrue(chessBoard.getPiece(1,4) instanceof Pawn);
        assertTrue(chessBoard.getPiece(1,5) instanceof Pawn);
        assertTrue(chessBoard.getPiece(1,6) instanceof Pawn);
        assertTrue(chessBoard.getPiece(1,7) instanceof Pawn);

        assertTrue(chessBoard.getPiece(7,0) instanceof Rook);
        assertTrue(chessBoard.getPiece(7,1) instanceof Knight);
        assertTrue(chessBoard.getPiece(7,2) instanceof Bishop);
        assertTrue(chessBoard.getPiece(7,3) instanceof Queen);
        assertTrue(chessBoard.getPiece(7,4) instanceof King);
        assertTrue(chessBoard.getPiece(7,5) instanceof Bishop);
        assertTrue(chessBoard.getPiece(7,6) instanceof Knight);
        assertTrue(chessBoard.getPiece(7,7) instanceof Rook);
        assertTrue(chessBoard.getPiece(6,0) instanceof Pawn);
        assertTrue(chessBoard.getPiece(6,1) instanceof Pawn);
        assertTrue(chessBoard.getPiece(6,2) instanceof Pawn);
        assertTrue(chessBoard.getPiece(6,3) instanceof Pawn);
        assertTrue(chessBoard.getPiece(6,4) instanceof Pawn);
        assertTrue(chessBoard.getPiece(6,5) instanceof Pawn);
        assertTrue(chessBoard.getPiece(6,6) instanceof Pawn);
        assertTrue(chessBoard.getPiece(6,7) instanceof Pawn);
    }

    @Test
    public void testRemovePiece() throws Exception {
        ChessBoard cb = new ChessBoard();
        Piece p = cb.getPiece(0,0);
        assertNull(p);
        cb.initialize();
        p = cb.getPiece(0,0);
        assertTrue(p instanceof Rook);
        cb.removePiece(0,0);
        p = cb.getPiece(0,0);
        assertNull(p);
    }
}