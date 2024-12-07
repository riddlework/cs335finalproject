/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: Player.java
 * Course: CSC335
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the controller--which communicates between the model and the view
 */
public class ScrabbleController {
    private ScrabbleModel model; // the model

    /**
     * A constructor--initialize the constructor with the model
     * @param model The model object
     */
    public ScrabbleController(ScrabbleModel model) {
        this.model = model;
    }

    /**
     * play a complete players turn--placement, validation, and score calculation
     * @param placementInfo the coordinates and letters to be placed at those coordinates
     * @return a boolean--whether the player's placement was valid or not
     */
    public boolean playerTurn(HashMap<Point,Character> placementInfo) {
        return model.playerTurn(placementInfo);
    }

    /**
     * return the winner of the game
     * @return an int representing the winner of the game
     */
    public int getWinner() {
        return model.getWinner();
    }

    /**
     * get the score of the current player
     * @return an int--the score of the current player
     */
    public int getCurPlayerScore() {
        return model.getCurPlayerScore();
    }

    /**
     * get the score for player one
     * @return an int--the score for player one
     */
    public int getPlayerOneScore() {
        return model.getPlayerOneScore();
    }

    /**
     * get the score for player wto
     * @return an int--the score for player two
     */
    public int getPlayerTwoScore() {
        return model.getPlayerTwoScore();
    }

    /**
     * return the hand of the current player
     * @return an arraylist of tiles--the hand of the current player
     */
    public ArrayList<Tile> getCurPlayerHand() {
        return model.getCurPlayerHand();
    }

    /**
     * switch players
     */
    public void switchPlayers() {
        model.switchPlayers();
    }

    /**
     * shuffle the player's hand
     */
    public void shufflePlayerHand() {
        model.shufflePlayerHand();
    }
}


