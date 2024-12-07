import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
        initDictionary();

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
    }

    // initialize the dictionary that is used to validate the words
    private void initDictionary() {
        try {
            Scanner inputScanner = new Scanner(new File("dictionary.txt"));
            while (inputScanner.hasNext()) dictionary.add(inputScanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("No such file exists!");
        }
    }

    public boolean isValidPlacement(int x1, int y1, int x2, int y2) {
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
            word = buildWord(x1, ts, x1, te);
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
            word = buildWord(ts, y1, te, x1);
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

//    private int calculateScore(int x1, int y1, int x2, int y2) {
//        int score = 0;
//        if (x1 == x2) {
//            // horizontal word
//
//        } else {
//
//        }
//    }

    private int findGap(int x, int y, int x_vec, int y_vec) {
        while(board.getBoardSquare(x,y).hasTile()) {
            x = x + x_vec;
            y = y + y_vec;
        }
        if (y_vec != 0) return y_vec; // searching vertically
        else return x_vec;            // searching horizontally
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
                BoardSquare bs = board.getBoardSquare(y1,i);
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
            letters.add(placementInfo.get(p));

            Character letter = placementInfo.get(p);
            Tile tileToAdd = p2.getTileByLetter(letter);
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
            else x2 = p.x;
            if (p.y < y1) y1 = p.y;
            else y2 = p.y;
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

    // shuffle player tiles
    // get player scores
    // get player hand

    public void switchPlayers() {
        if (curPlayer == p1) curPlayer = p2;
        else curPlayer = p1;
    }

}



