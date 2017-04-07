import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {
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
        Piece p1 = new King(Color.BLACK);
        Piece p2 = new King(Color.WHITE);
        assertEquals(Color.BLACK, p1.getColor());
        assertEquals(Color.WHITE, p2.getColor());
        assertEquals(4, p1.getX());
        assertEquals(0, p1.getY());
        assertEquals(4, p2.getX());
        assertEquals(7, p2.getY());
    }

    @Test
    public void  testPossibleKingMoves() throws Exception {
        Piece p = new King(Color.BLACK);
        assertFalse(p.isValidMove(p.getX(),p.getY())); // No move
        assertTrue(p.isValidMove(p.getX()+1,p.getY()+1)); //Diagonal move
        assertFalse(p.isValidMove(p.getX(),p.getY()-1)); // Out of bounds move
        assertFalse(p.isValidMove(p.getX()-2,p.getY())); //Too long x-dir move
        assertFalse(p.isValidMove(p.getX(),p.getY()+2)); //Too long y-dir move
    }

    @Test
    public void testMakePawn() throws Exception {
        Piece p1 = new Pawn(Color.BLACK, 1);
        Piece p2 = new Pawn(Color.WHITE, 2);
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
        Piece p1 = new Pawn(Color.BLACK, 0);
        Piece p2 = new Pawn(Color.WHITE, 0);
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

        p1.moveTo(p1.getX(),p1.getY()+1);
        p2.moveTo(p2.getX(),p2.getY()-1);
        assertFalse(p1.isValidMove(p1.getX(), p1.getY()+2)); // Only allowed first time
        assertFalse(p2.isValidMove(p2.getX(), p2.getY()-2)); // Only allowed first time
    }
}

