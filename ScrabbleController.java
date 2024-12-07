import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ScrabbleController {
    private ScrabbleModel model;

    public ScrabbleController(ScrabbleModel model) {
        this.model = model;
    }

    public boolean playerTurn(HashMap<Point,Character> placementInfo) {
        return model.playerTurn(placementInfo);
    }

    public int getWinner() {
        return model.getWinner();
    }

    public int getCurPlayerScore() {
        return model.getCurPlayerScore();
    }

    public int getPlayerOneScore() {
        return model.getPlayerOneScore();
    }

    public int getPlayerTwoScore() {
        return model.getPlayerTwoScore();
    }

    public ArrayList<Tile> getCurPlayerHand() {
        return model.getCurPlayerHand();
    }

    public void switchPlayers() {
        model.switchPlayers();
    }

    public void shufflePlayerHand() {
        model.shufflePlayerHand();
    }
}


