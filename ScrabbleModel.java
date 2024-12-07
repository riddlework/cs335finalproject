import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ScrabbleModel {
    private Board board;
    private Player p1;
    private Player p2;
    private Player curPlayer; // current player
    private DrawPile drawPile;
    private ArrayList<String> dictionary;

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

    // initialize the dictionary that is used to validate the words
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

    
    private int findGap(int x, int y, int x_vec, int y_vec) {
        while(board.getBoardSquare(x,y).hasTile()) {
            x = x + x_vec;
            y = y + y_vec;
        }
        if (y_vec != 0) return y-y_vec; // searching vertically
        else return x-x_vec;            // searching horizontally
    }


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

    
    
    
    
    
    
    

    // returns whether placement was invalid or not--if invalid it is still the given player's turn
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

    public void swapTiles(ArrayList<Character> tilesToBeSwapped) {
        for (Character letter: tilesToBeSwapped) {
            try {
                curPlayer.replaceTileByLetter(letter, drawPile.drawTile());
            } catch (InvalidDrawException e) {
                System.out.println("No more tiles! Draw pile is empty.");
            }
        }
    }

    public void switchPlayers() {
        if (curPlayer == p1) curPlayer = p2;
        else curPlayer = p1;
    }
    
    
    public void shufflePlayerHand(Player player) {
    	player.shuffleHand();
    }
    
    
    public ArrayList<Tile> getPlayerHand(Player player) {
    	return player.getHand();
    }

    // return the current players score
    public int getCurPlayerScore() {
        return curPlayer.getScore();
    }

    
    public void setPlayerHand(ArrayList<Tile> hand) {
    	p1.setHand(hand);
    }
    
    
    public void setStartP1() {
    	curPlayer = p1;
    }


    public int getWinner() {
        int p1Score = p1.getScore();
        int p2Score = p2.getScore();
        if (p1Score > p2Score) return 1;
        else if (p2Score > p1Score) return 2;
        else return 0;
    }

    public int getPlayerOneScore() {
        return p1.getScore();
    }

    public int getPlayerTwoScore() {
        return p2.getScore();
    }
    	
    
    public Player getCurPlayer() {
    	return curPlayer;
    }
    
    	
}



