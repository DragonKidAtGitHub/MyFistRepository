import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessGameTest {
    @Test
    public void testSetupGame() throws Exception {
        ChessGame g = new ChessGame();
        g.setupGame();

        String gameString = createGameString();
        assertEquals(gameString, g.getGame());
    }

    @Test
    public void testSetupGameTwice() throws Exception {
        ChessGame g = new ChessGame();
        g.setupGame();
        g.setupGame();

        String gameString = createGameString();
        assertEquals(gameString,g.getGame());
    }

    @Test
    public void testFault() throws Exception {
        assertEquals(0,1);
    }

    private String createGameString() {
        String gameString = "";
        String emptySymbol = "*";
        String row = "";
        String newLine = System.lineSeparator();
        for (int i = 0; i < 8; i++) {
            row += emptySymbol;
        }
        for (int i = 0; i < 8; i++) {
            gameString += row;
            if(i < 7) {
                gameString += newLine;
            }
        }
        return gameString;
    }
}

