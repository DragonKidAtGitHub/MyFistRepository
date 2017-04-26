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
    }

    @Test
    public void  testPossibleKingMoves() throws Exception {
        King p = new King(Color.BLACK);
        assertFalse(p.isValidMove(0,0,0,0)); // No move
        assertTrue(p.isValidMove(4,0, 5,1)); //Diagonal move
        assertFalse(p.isValidMove(4,0,4,-1)); // Out of bounds move
        assertFalse(p.isValidMove(4,0,6,0)); //Too long x-dir move
        assertFalse(p.isValidMove(4,0,4,2)); //Too long y-dir move
    }
    @Test
    public void testMakePawn() throws Exception {
        Pawn p1 = new Pawn(Color.BLACK);
        Pawn p2 = new Pawn(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
    }

    
    @Test
    public void testPossibleMovePawn() throws Exception {
        Piece p1 = new Pawn(Color.BLACK);
        Piece p2 = new Pawn(Color.WHITE);
        assertFalse(p1.isValidMove(2,0,2,0));       // No move
        assertFalse(p1.isValidMove(2,0,-1, 0));         // Out of bounds
        assertFalse(p1.isValidMove(2,0,8, 0));          // Out of bounds
        assertFalse(p1.isValidMove(2,0,2, -1));         // Out of bounds
        assertFalse(p1.isValidMove(2,0,-1, 8));             // Out of bounds
        assertTrue(p1.isValidMove(2,0,2,0+1));  // Normal move
        assertTrue(p2.isValidMove(2,7,2,7-1));  // Normal move
        assertFalse(p1.isValidMove(2,0,2,0-1)); // Wrong direction move
        assertFalse(p2.isValidMove(2,7,2,7+1)); // Wrong direction move
        assertTrue(p1.isValidMove(2,0,2, 0+2)); // Allowed first time
        assertTrue(p2.isValidMove(2,7,2, 7-2)); // Allowed first time
        assertTrue(p1.isValidMove(2,0,2+1,0+1)); // Diagonal move
        assertTrue(p1.isValidMove(2,0,2-1,0+1)); // Diagonal move
        assertTrue(p2.isValidMove(2,7,2+1,7-1)); // Diagonal move
        assertTrue(p2.isValidMove(2,7,2-1,7-1)); // Diagonal move
        assertFalse(p1.isValidMove(2,0,2+1,0-1)); // Diagonal move
        assertFalse(p1.isValidMove(2,0,2-1,0-1)); // Diagonal move
        assertFalse(p2.isValidMove(2,7,2+1,7+1)); // Diagonal move
        assertFalse(p2.isValidMove(2,7,2-1,7+1)); // Diagonal move
    }

    @Test
    public void testMakeQueen() throws Exception {
        Piece p1 = new Queen(Color.BLACK);
        Piece p2 = new Queen(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
    }

    @Test
    public void  testPossibleQueenMoves() throws Exception {
        Piece p1 = new Queen(Color.BLACK);
        Piece p2 = new Queen(Color.WHITE);
        assertFalse(p1.isValidMove(2,0,2,0));               // No move
        assertTrue(p1.isValidMove(2,0,2+1,0+1));    //Diagonal move
        assertTrue(p1.isValidMove(2,0,2-2,0+2));    //Diagonal move
        assertTrue(p1.isValidMove(2,0,2+4,0+4));    //Diagonal move
        assertTrue(p1.isValidMove(2,0,2+4,0));          //Straight move
        assertTrue(p1.isValidMove(2,0,2,0+3));          //Straight move
        assertFalse(p1.isValidMove(2,0,2,0-1));         // Out of bounds move
        assertFalse(p1.isValidMove(2,0,2,0+8));         // Out of bounds move

        assertFalse(p2.isValidMove(2,7,2,7));               // No move
        assertTrue(p2.isValidMove(2,7,2+1,7-1));    //Diagonal move
        assertTrue(p2.isValidMove(2,7,2-2,7-2));    //Diagonal move
        assertTrue(p2.isValidMove(2,7,2+4,7-4));    //Diagonal move
        assertTrue(p2.isValidMove(2,7,2+4,7));          //Straight move
        assertTrue(p2.isValidMove(2,7,2,7-3));          //Straight move
        assertFalse(p2.isValidMove(2,7,2,7+1));         // Out of bounds move
        assertFalse(p2.isValidMove(2,7,2,7-8));         // Out of bounds move
    }

    @Test
    public void testMakeRook() throws Exception {
        Piece p1 = new Rook(Color.BLACK);
        Piece p2 = new Rook(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
    }

    @Test
    public void  testPossibleRookMoves() throws Exception {
        Piece p1 = new Rook(Color.BLACK);
        Piece p2 = new Rook(Color.WHITE);
        assertFalse(p1.isValidMove(2,0,2,0));               // No move
        assertFalse(p1.isValidMove(2,0,2+1,0+1));    //Diagonal move
        assertFalse(p1.isValidMove(2,0,2-3,0+3));    //Diagonal move
        assertTrue(p1.isValidMove(2,0,2+4,0));          //Straight move
        assertTrue(p1.isValidMove(2,0,2,0+3));          //Straight move
        assertFalse(p1.isValidMove(2,0,2,0-1));         // Out of bounds move
        assertFalse(p1.isValidMove(2,0,2,0+8));         // Out of bounds move

        assertFalse(p2.isValidMove(2,7,2,7));               // No move
        assertFalse(p2.isValidMove(2,7,2+1,7-1));    //Diagonal move
        assertFalse(p2.isValidMove(2,7,2-3,7-3));    //Diagonal move
        assertTrue(p2.isValidMove(2,7,2-2,7));          //Straight move
        assertTrue(p2.isValidMove(2,7,2,7-3));          //Straight move
        assertFalse(p2.isValidMove(2,7,2,7+1));         // Out of bounds move
        assertFalse(p2.isValidMove(2,7,2,7-8));         // Out of bounds move
    }

    @Test
    public void testMakeBishop() throws Exception {
        Piece p1 = new Bishop(Color.BLACK);
        Piece p2 = new Bishop(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
    }

    @Test
    public void  testPossibleBishopMoves() throws Exception {
        Piece p1 = new Bishop(Color.BLACK);
        Piece p2 = new Bishop(Color.WHITE);
        assertFalse(p1.isValidMove(2,0,2,0));               // No move
        assertTrue(p1.isValidMove(2,0,2+1,0+1));    //Diagonal move
        assertTrue(p1.isValidMove(2,0,2-2,0+2));    //Diagonal move
        assertFalse(p1.isValidMove(2,0,2+4,0));          //Straight move
        assertFalse(p1.isValidMove(2,0,2,0+3));          //Straight move
        assertFalse(p1.isValidMove(2,0,2,0-1));         // Out of bounds move
        assertFalse(p1.isValidMove(2,0,2,0+8));         // Out of bounds move

        assertFalse(p2.isValidMove(2,7,2,7));               // No move
        assertTrue(p2.isValidMove(2,7,2+1,7-1));    //Diagonal move
        assertTrue(p2.isValidMove(2,7,2-2,7-2));    //Diagonal move
        assertFalse(p2.isValidMove(2,7,2-2,7));          //Straight move
        assertFalse(p2.isValidMove(2,7,2,7-3));          //Straight move
        assertFalse(p2.isValidMove(2,7,2,7+1));         // Out of bounds move
        assertFalse(p2.isValidMove(2,7,2,7-8));         // Out of bounds move
    }

    @Test
    public void testMakeKnight() throws Exception {
        Piece p1 = new Knight(Color.BLACK);
        Piece p2 = new Knight(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());

    }

    @Test
    public void  testPossibleKnightMoves() throws Exception {
        Piece p1 = new Knight(Color.BLACK);
        Piece p2 = new Knight(Color.WHITE);
        assertFalse(p1.isValidMove(2,0,2,0));                   // No move
        assertFalse(p1.isValidMove(2,0,2+1,0+1));       //Diagonal move
        assertFalse(p1.isValidMove(2,0,2-2,0+2));       //Diagonal move
        assertFalse(p1.isValidMove(2,0,2+4,0));             //Straight move
        assertFalse(p1.isValidMove(2,0,2,0+3));             //Straight move
        assertFalse(p1.isValidMove(2,0,2,0-1));             // Out of bounds move
        assertFalse(p1.isValidMove(2,0,2,0+8));             // Out of bounds move
        assertTrue(p1.isValidMove(2,0,2+1,0+2));        // Knight move
        assertTrue(p1.isValidMove(2,0,2-1,0+2));        // Knight move
        assertTrue(p1.isValidMove(2,0,2+2,0+1));        // Knight move

        assertFalse(p2.isValidMove(2,7,2,7));                   // No move
        assertFalse(p2.isValidMove(2,7,2+1,7-1));       //Diagonal move
        assertFalse(p2.isValidMove(2,7,2-3,7-3));       //Diagonal move
        assertFalse(p2.isValidMove(2,7,2-4,7));             //Straight move
        assertFalse(p2.isValidMove(2,7,2,7-3));             //Straight move
        assertFalse(p2.isValidMove(2,7,2,7+1));             // Out of bounds move
        assertFalse(p2.isValidMove(2,7,2,7-8));             // Out of bounds move
        assertTrue(p2.isValidMove(2,7,2+1,7-2));        // Knight move
        assertTrue(p2.isValidMove(2,7,2-1,7-2));        // Knight move
        assertTrue(p2.isValidMove(2,7,2+2,7-1));        // Knight move
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