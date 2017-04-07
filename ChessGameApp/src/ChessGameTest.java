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

        Piece p3 = new Pawn(Color.BLACK, -1);

    }
}

