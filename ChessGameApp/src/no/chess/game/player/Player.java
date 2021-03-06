package no.chess.game.player;
/**
 * Created by Ulrik on 31-May-17.
 */
public class Player {
    private String name;
    private int noOfWins = 0;
    private int noOfLoss = 0;
    private int noOfDraws = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public void increaseWinNumber() {
        noOfWins++;
    }

    public void increaseLossNumber() {
        noOfLoss++;
    }

    public void increaseDrawNumber() {
        noOfDraws++;
    }
}
