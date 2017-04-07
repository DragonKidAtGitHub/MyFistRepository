/**
 * Created by ujo on 06.04.2017.
 */
public class ChessBoard {
    final int rows = 8;
    final int columns = 8;
    private Spot[][] spots = new Spot[rows][columns];

    public ChessBoard() {
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                boolean rowIsEven = ((row % 2) == 0);
                boolean columnIsEven = ((column % 2) == 0);
                if (rowIsEven){
                    if (columnIsEven){
                        spots[row][column] = new Spot(Color.WHITE);
                    }
                    else{
                        spots[row][column] = new Spot(Color.BLACK);
                    }
                }
                else {
                    if (columnIsEven) {
                        spots[row][column] = new Spot(Color.WHITE);
                    } else {
                        spots[row][column] = new Spot(Color.BLACK);
                    }
                }
            }
        }
    }
}
