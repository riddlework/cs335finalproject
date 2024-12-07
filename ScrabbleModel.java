/**
 * Author(s): Ben Yurek, Maria Fay Garcia, Lucas Dargert, Mohamed Diakhate
 * File: ScrabbleModel.java
 * Course: CSC335
 */
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the model class and handles the back-end of the scrabble game
 */
public class ScrabbleModel {
    private Board board;
    private Player p1;
    private Player p2;
    private Player curPlayer; // current player
    private DrawPile drawPile;
    private ArrayList<String> dictionary;

    /**
     * Initialize the scrabble model along with the players, board, draw pile, etc.
     */
    public ScrabbleModel() {
        // initialize dictionary
        dictionary = initDictionary();

        board = new Board();
        drawPile = new DrawPile();
        try {
            ArrayList<Tile> p1Rack = drawPile.drawTiles(7);
            ArrayList<Tile> p2Rack = drawPile.drawTiles(7);
            p1 = new Player("p1", p1Rack);
            p2 = new Player("p2", p2Rack);
        } catch (InvalidDrawException e) {
            System.out.println("No tiles. Please initialize the draw pile first!");
        }

        // choose random first player
        Random rand = new Random();
        int randInt = rand.nextInt(2) + 1;
        if (randInt == 1) curPlayer = p1;
        else curPlayer = p2;
    }


    /**
     * Initialize the dictionary that is used to validate the words
     * @return an ArrayList of Strings, the dictionary used to validate words
     */
    private ArrayList<String> initDictionary() {
    	ArrayList<String> newDict = new ArrayList<>();
        try {
            Scanner inputScanner = new Scanner(new File("dictionary.txt"));
            while (inputScanner.hasNext()) newDict.add(inputScanner.nextLine().toLowerCase());
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists!");
        }
        return newDict;
    }

    /**
     * Checks if the placement of a word is valid
     * @param x1 an int--the first x-coordinate of the placement
     * @param y1 an int--the first y-coordinate of the placement
     * @param x2 an int--the second x-coordinate of the placement
     * @param y2 an int--the second y-coordinate of the placement
     * @return a boolean--whether or not the placement was valid
     */
    private boolean isValidPlacement(int x1, int y1, int x2, int y2) {
        int ts, te; // true start, true end
        String word;
        if (x1 == x2 && y1 == y2) {
            int v_ts, v_te, h_ts, h_te; // true starts and ends

            // find the start and end of a vertical word
            v_ts = findGap(x1, y1, 0, -1);
            v_te = findGap(x1, y1, 0, 1);

            // build the word and check if it is valid, if there is one
            if (v_ts != v_te) {
                word = buildWord(x1, v_ts, x1, v_te);
                if (!dictionary.contains(word)) return false;
            }

            // find the start and end of a horizontal word
            h_ts = findGap(x1, y1, -1, 0);
            h_te = findGap(x1, y1, 1, 0);

            // build the word and check if it is valid, if there is one
            if (h_ts != h_te) {
                word = buildWord(h_ts, y1, h_te, y2);
                if (!dictionary.contains(word)) return false;
            }

            // check that a single letter has not been placed
            if (v_ts == v_te && v_te == h_ts && h_ts == h_te) return false;

        } else if (x1 == x2) {
            // vertical placement

            // find the start, end of the word
            ts = findGap(x1, y1, 0, -1);
            te = findGap(x1, y2, 0, 1);

            // build the word and check if it is valid
            word = buildWord(x1, x2, ts, te);
            if (!dictionary.contains(word)) return false;

            // check cross words
            for (int y = y1; y <= y2; y++) {
                // find the start, end of the word
                ts = findGap(x1, y, -1, 0);
                te = findGap(x1, y, 1, 0);

                // build the word and check if it is valid
                if (ts != te) {
                    word = buildWord(x1, ts, x2, te);
                    if (!dictionary.contains(word)) return false;
                }
            }
        } else {
            // horizontal placement

            // find the start, end of the word
            ts = findGap(x1, y1, -1, 0);
            te = findGap(x2, y1, 1, 0);

            // build the word and check if it is valid
            word = buildWord(ts, te, y1, y2);
            
            if (!dictionary.contains(word)) return false;

            // check cross words
            for (int x = x1; x <= x2; x++) {
                // find the start, end of the word
                ts = findGap(x, y1, 0, -1);
                te = findGap(x, y1, 0, 1);

                // build the word and check if it is valid
                if (ts != te) {
                    word = buildWord(ts, y1, te, y2);
                    if (!dictionary.contains(word)) return false;
                }
            }
        } return true;
    }

    /**
     * Calculate the score of a placement
     * @param x1 an int--the first x-coordinate of the placement
     * @param y1 an int--the first y-coordinate of the placement
     * @param x2 an int--the second x-coordinate of the placement
     * @param y2 an int--the second y-coordinate of the placement
     * @param startScore an int--the starting score
     * @return an int-- the updated score
     */
    private int calculateScore(int x1, int y1, int x2, int y2, int startScore) {
        int ts, te;
        if (x1 == x2) {
            // vertical word

            // find the start, end of the word
            ts = findGap(x1, y1, 0, -1);
            te = findGap(x1, y2, 0, 1);

            // add the startScore of the word to the total startScore
            startScore += getWordScore(x1, x1, ts, te);

            // add the scores of the crosswords
            for (int y = y1; y <= y2; y++) {
                // find the start, end of the word
                ts = findGap(x1, y, -1, 0);
                te = findGap(x1, y, 1, 0);

                // build the word and check if it is valid
                if (ts != te) startScore += getWordScore(ts, te, y1, y1);
            }
        } else if (y1 == y2) {
            // horizontal placement

            // find the start, end of the word
            ts = findGap(x1, y1, -1, 0);
            te = findGap(x2, y1, 1, 0);

            // build the word and check if it is valid
            startScore += getWordScore(ts, te, y1, y1);

            // check cross words
            for (int x = x1; x <= x2; x++) {
                // find the start, end of the word
                ts = findGap(x, y1, 0, -1);
                te = findGap(x, y1, 0, 1);

                // build the word and check if it is valid
                if (ts != te)  startScore += getWordScore(x1, x1, ts, te);
            }
        } return startScore;
    }

    /**
     * get the score of a word
     * @param x1 an int--the first x-coordinate of the placement
     * @param y1 an int--the first y-coordinate of the placement
     * @param x2 an int--the second x-coordinate of the placement
     * @param y2 an int--the second y-coordinate of the placement
     * @return an int--the score of the word, factoring in multipliers
     */
    private int getWordScore(int x1, int x2, int y1, int y2) {
        int score = 0;
        if (x1 == x2) {
            // vertical word
            for (int i = y1; i <= y2; i++) {
                BoardSquare bs = board.getBoardSquare(x1,i);
                score += bs.getScore();
            }
        } else {
            // horizontal word
            for (int i = x1; i <= x2; i++) {
                BoardSquare bs = board.getBoardSquare(i,y1);
                score += bs.getScore();
            }
        } return score;
    }

    /**
     * Find the ends of a word
     * @param x an int--the x coordinate
     * @param y an int--the y coordinate
     * @param x_vec an int--whether to go forwards or backwards
     * @param y_vec an int--whether to go forwards or backwards
     * @return an int--the desired index at the end of the word
     */
    private int findGap(int x, int y, int x_vec, int y_vec) {
        while(board.getBoardSquare(x,y).hasTile()) {
            x = x + x_vec;
            y = y + y_vec;
        }
        if (y_vec != 0) return y-y_vec; // searching vertically
        else return x-x_vec;            // searching horizontally
    }


    /**
     * build a word given starting and ending indices
     * @param x1 an int--the first x-coordinate of the placement
     * @param y1 an int--the first y-coordinate of the placement
     * @param x2 an int--the second x-coordinate of the placement
     * @param y2 an int--the second y-coordinate of the placement
     * @return a String--the word
     */
    private String buildWord(int x1, int x2, int y1, int y2) {
        String word = "";
        if (x1 == x2) {
            // vertical word
            for (int i = y1; i <= y2; i++) {
                BoardSquare bs = board.getBoardSquare(x1,i);
                word += bs.getLetter();
            }
        } else {
            // horizontal word
            for (int i = x1; i <= x2; i++) {
                BoardSquare bs = board.getBoardSquare(i,y1);
                word += bs.getLetter();
            }
        } return word;
    }

    

    /**
     * play a complete players turn--placement, validation, and score calculation
     * @param placementInfo the coordinates and letters to be placed at those coordinates
     * @return a boolean--whether the player's placement was valid or not
     */
    public boolean playerTurn(HashMap<Point,Character> placementInfo) {

        // add tiles to the board from the players hand
        ArrayList<Point> coords = new ArrayList<>();
        ArrayList<Character> letters = new ArrayList<>();
        for (Point p: placementInfo.keySet()) {
            coords.add(p);
            Character letter = placementInfo.get(p);
            letters.add(letter);
            
            Tile tileToAdd = curPlayer.getTileByLetter(letter);
            board.addTile(tileToAdd, p.x, p.y);
        }

        // find the start and end indices
        int x1, x2, y1, y2;
        x1 = coords.get(0).x;
        x2 = coords.get(0).x;
        y1 = coords.get(0).y;
        y2 = coords.get(0).y;
        for (Point p: coords) {
            if (p.x < x1) x1 = p.x;
            if (p.x > x2) x2 = p.x;
            if (p.y < y1) y1 = p.y;
            if (p.y > y2) y2 = p.y;
        }

        // validate the placement
        if (!isValidPlacement(x1, y1, x2, y2)) {
            // remove the tiles from the board
            for (Point p: coords) board.removeTile(p.x, p.y);

            // report unsuccessful placement
            return false;
        }

        // placement is valid
        // remove the tiles from the player's hand
        // replace them with new tiles
        swapTiles(letters);

        // calculate the score of the placement
        // add it to the players score
        int curPlayerCurScore = curPlayer.getScore();
        curPlayer.setScore(calculateScore(x1, y1, x2, y2, curPlayerCurScore));

        return true;
    }

    /**
     * swap the player's desired tiles
     * @param tilesToBeSwapped an array list of characters--the tiles the player desires to swap
     */
    public void swapTiles(ArrayList<Character> tilesToBeSwapped) {
        for (Character letter: tilesToBeSwapped) {
            try {
                curPlayer.replaceTileByLetter(letter, drawPile.drawTile());
            } catch (InvalidDrawException e) {
                System.out.println("No more tiles! Draw pile is empty.");
            }
        }
    }

    /**
     * switch players
     */
    public void switchPlayers() {
        if (curPlayer == p1) curPlayer = p2;
        else curPlayer = p1;
    }

    /**
     * shuffle the players hand
     */
    public void shufflePlayerHand() {
    	curPlayer.shuffleHand();
    }

    /**
     * return the player's hand of tiles
     * @param player the player whose hand we want
     * @return an arraylist--the tiles that make up the player's hand
     */
    public ArrayList<Tile> getPlayerHand(Player player) {
    	return player.getHand();
    }

    // return the current players score

    /**
     * return the current player's score
     * @return an int--the current player's score
     */
    public int getCurPlayerScore() {
        return curPlayer.getScore();
    }


    /**
     * set the player's hand to the given tiles
     * this function is for unit testing purposes
     * @param hand an arraylist of tiles--the tiles to set the player's hand to
     */
    public void setPlayerHand(ArrayList<Tile> hand) {
    	p1.setHand(hand);
    }


    /**
     * set player 1 to start the game
     * this function is for unit testing purposes
     */
    public void setStartP1() {
    	curPlayer = p1;
    }


    /**
     * return the winner
     *  0 -- TIE
     *  1 -- PLAYER 1
     *  2 -- PLAYER 2
     * @return an int representing the player who won
     */
    public int getWinner() {
        int p1Score = p1.getScore();
        int p2Score = p2.getScore();
        if (p1Score > p2Score) return 1;
        else if (p2Score > p1Score) return 2;
        else return 0;
    }

    /**
     * return the score of player one
     * @return an int--player 1's score
     */
    public int getPlayerOneScore() {
        return p1.getScore();
    }

    /**
     * return the score of player two
     * @return an int--player 2's score
     */
    public int getPlayerTwoScore() {
        return p2.getScore();
    }

    /**
     * get the hand of the current player
     * @return an array
     */
    public ArrayList<Tile> getCurPlayerHand() {
        return curPlayer.getHand();
    }


    /**
     * return the current player
     * @return A player object--the current player
     */
    public Player getCurPlayer() {
    	return curPlayer;
    }
    
    	
}



