public class ChessGame {
    final int gameRows = 8;
    final int gameColumns = 8;

    private String game;


    public ChessGame() {
        game = "";
    }

    public void setupGame()
    {
        if (game.isEmpty()) {
            String newLine = System.lineSeparator();
            String emptySymbol = "*";
            String row = "";
            for (int i = 0; i < gameRows; i++) {
                row += emptySymbol;
            }
            for (int i = 0; i < gameColumns; i++) {
                game += row;
                if(i < gameColumns-1) {
                    game += newLine;
                }
            }
        }
    }

    public String getGame() {
        return game;
    }
}
