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

    public ArrayList<Tile> getPlayerOneHand() {
        return model.getPlayerOneHand();
    }

    public ArrayList<Tile> getPlayerTwoHand() {
        return model.getPlayerTwoHand();
    }
}


